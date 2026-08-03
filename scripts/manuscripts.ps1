<#
.SYNOPSIS
Collects PaintTool SAI 2 source files (.sai2) from the resources textures into
a "manuscripts" folder, preserving the original folder structure.

.DESCRIPTION
Scans <Root> recursively for *.sai2 files that live inside a "textures"
directory (i.e. src/main/resources/assets/<modid>/textures/...) and copies each
one into <Out>/<relative-path-from-Root>, mirroring the directory layout. This
lets you archive the editable SAI sources without disturbing the in-repo
textures that the game loads.

Example result for:
    src/main/resources/assets/gtlitecore/textures/blocks/stones/a.sai2
becomes:
    manuscripts/assets/gtlitecore/textures/blocks/stones/a.sai2

.PARAMETER Root
Folder to scan for .sai2 files.
Default: src/main/resources

.PARAMETER Out
Destination folder (created if missing).
Default: manuscripts

.PARAMETER Force
Overwrite files that already exist in <Out>. Without it, existing files are
skipped.

.PARAMETER Move
Move the files instead of copying them (removes the originals).

.PARAMETER DryRun
Print what would be done without touching the disk.

.EXAMPLE
powershell -ExecutionPolicy Bypass -File scripts/manuscripts.ps1

.EXAMPLE
powershell -ExecutionPolicy Bypass -File scripts/manuscripts.ps1 -DryRun -Force
#>
[CmdletBinding()]
param(
    [string]$Root,
    [string]$Out,
    [switch]$Force,
    [switch]$Move,
    [switch]$DryRun
)

$ErrorActionPreference = 'Stop'

$RepoRoot = Split-Path -Parent $PSScriptRoot
if (-not $Root) { $Root = Join-Path $RepoRoot 'src\main\resources' }
if (-not $Out) { $Out = Join-Path $RepoRoot 'manuscripts' }

$rootDir = [System.IO.Path]::GetFullPath($Root)
$outDir = [System.IO.Path]::GetFullPath($Out)

if (-not (Test-Path -LiteralPath $rootDir)) {
    Write-Error "Scan root not found: $rootDir"
    exit 1
}

$files = @(Get-ChildItem -LiteralPath $rootDir -Recurse -File |
    Where-Object { $_.Extension -ieq '.sai2' } |
    Where-Object {
        # Only keep files sitting under a "textures" directory.
        $segments = $_.FullName.Substring($rootDir.TrimEnd('\').Length + 1) -split '[\\/]'
        $segments -contains 'textures'
    })

if ($files.Count -eq 0) {
    Write-Host "No .sai2 files under a textures folder found in $rootDir"
    exit 0
}

$copied = 0
$moved = 0
$skipped = 0

foreach ($file in $files) {
    $rel = $file.FullName.Substring($rootDir.TrimEnd('\').Length + 1)
    $dest = Join-Path $outDir $rel
    $existed = Test-Path -LiteralPath $dest

    $action = if ($Move) { 'move' } else { 'copy' }

    if ($existed -and -not $Force) {
        $skipped++
        Write-Host "skip  $rel (already exists, use -Force)"
        continue
    }

    if ($DryRun) {
        Write-Host "[dry] $action $rel"
        continue
    }

    New-Item -ItemType Directory -Force -Path (Split-Path -Parent $dest) | Out-Null
    if ($Move) {
        Move-Item -LiteralPath $file.FullName -Destination $dest -Force
        $moved++
        Write-Host "move  $rel"
    }
    else {
        Copy-Item -LiteralPath $file.FullName -Destination $dest -Force
        $copied++
        Write-Host "copy  $rel"
    }
}

Write-Host "`nDone: $copied copied, $moved moved, $skipped skipped."
Write-Host "Destination: $outDir"
