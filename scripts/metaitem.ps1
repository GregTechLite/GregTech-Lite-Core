<#
.SYNOPSIS
Generates MetaItem model JSON files into resources folder.

.DESCRIPTION
For each item name this script writes <Out>/<name>.json containing the
standard GTCEu "generated item" model that points at the matching texture:

    {
      "parent": "item/generated",
      "textures": { "layer0": "gtlitecore:items/metaitems/<name>" }
    }

The corresponding PNG texture is NOT created by this script.

.PARAMETER Items
One or more item unlocalized names to generate models for. A per-item texture
override can be given as "name=texture", e.g.
"battery.max.infinity=items/metaitems/battery.max.infinity/1".

.PARAMETER Out
Output directory for the JSON files.
Default: src/main/resources/assets/gtlitecore/models/item/metaitems

.PARAMETER ModId
Mod id used to prefix texture paths. Default: gtlitecore

.PARAMETER Parent
Model parent; use "item/handheld" for tools. Default: item/generated

.PARAMETER Texture
Texture applied to ALL items. Prefixed with "<modid>:" unless it already
contains a colon. Default: items/metaitems/<name>

.PARAMETER Force
Overwrite JSON files that already exist.

.PARAMETER DryRun
Print what would be written without touching the disk.

.EXAMPLE
powershell -ExecutionPolicy Bypass -File scripts/metaitem.ps1 tool.disposable.saw circuit.advanced

.EXAMPLE
.\scripts\metaitem.ps1 battery.x=items/metaitems/battery.x/1 -Parent item/handheld -Force
#>
[CmdletBinding()]
param(
    [Parameter(Position = 0, ValueFromRemainingArguments = $true)]
    [string[]]$Items,

    [string]$Out,
    [string]$ModId = 'gtlitecore',
    [string]$Parent = 'item/generated',
    [string]$Texture,
    [switch]$Force,
    [switch]$DryRun
)

$ErrorActionPreference = 'Stop'

$RepoRoot = Split-Path -Parent $PSScriptRoot
if (-not $Out) {
    $Out = Join-Path $RepoRoot 'src\main\resources\assets\gtlitecore\models\item\metaitems'
}
$OutDir = [System.IO.Path]::GetFullPath($Out)

if (-not $Items) {
    Write-Error "No item names given.`nRun: Get-Help $PSCommandPath -Full"
    exit 1
}

$NamePattern = '^[a-z0-9][a-z0-9_.\-/]*$'
$invalid = @()
foreach ($arg in $Items) {
    $eq = $arg.IndexOf('=')
    $n = if ($eq -ge 0) { $arg.Substring(0, $eq) } else { $arg }
    if ($n -notmatch $NamePattern) { $invalid += $n }
}
if ($invalid.Count -gt 0) {
    Write-Error ("Invalid item name(s): {0}`nNames may only contain lowercase letters, digits, dots, underscores, dashes and slashes." -f ($invalid -join ', '))
    exit 1
}

function ConvertTo-MetaItemModelJson {
    param([string]$Parent, [string]$Layer0)

    $p = $Parent -replace '\\', '\\' -replace '"', '\"'
    $l = $Layer0 -replace '\\', '\\' -replace '"', '\"'

    return "{" + "`n" +
        '  "parent": "' + $p + '",' + "`n" +
        '  "textures": {' + "`n" +
        '    "layer0": "' + $l + '"' + "`n" +
        '  }' + "`n" +
        '}' + "`n"
}

$created = 0
$updated = 0
$skipped = 0

foreach ($arg in $Items) {
    $eq = $arg.IndexOf('=')
    if ($eq -ge 0) {
        $name = $arg.Substring(0, $eq)
        $override = $arg.Substring($eq + 1)
    }
    else {
        $name = $arg
        $override = $null
    }

    $file = Join-Path $OutDir ($name + '.json')
    $existed = Test-Path -LiteralPath $file

    $raw = if ($override) { $override } elseif ($Texture) { $Texture } else { "items/metaitems/$name" }
    $layer0 = if ($raw.Contains(':')) { $raw } else { "$ModId`:$raw" }

    $json = ConvertTo-MetaItemModelJson -Parent $Parent -Layer0 $layer0

    if ($existed -and -not $Force) {
        $skipped++
        Write-Host "skip   $file (already exists, use -Force)"
        continue
    }

    if ($DryRun) {
        Write-Host "[dry]  $file"
        continue
    }

    New-Item -ItemType Directory -Force -Path (Split-Path -Parent $file) | Out-Null
    [System.IO.File]::WriteAllText($file, $json, (New-Object System.Text.UTF8Encoding($false)))

    if ($existed) {
        $updated++
        Write-Host "update $file"
    }
    else {
        $created++
        Write-Host "write  $file"
    }
}

Write-Host "`nDone: $created created, $updated updated, $skipped skipped."
Write-Host "Output directory: $OutDir"
