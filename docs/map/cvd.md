## Laser CVD recipe rule

```kotlin
var originalDuration = 25 * SECOND
var originalTemperature = 1023

// Ga2O3 + 2NH3 -> 2GaN + 3H2O
CVD_RECIPES.recipeBuilder()
    .input(dust, GalliumTrioxide, 5)
    .fluidInputs(Ammonia.getFluid(2000))
    .output(plate, GalliumNitride, 4)
    .fluidOutputs(Steam.getFluid(3000))
    .EUt(VA[LuV].toLong())
    .duration(originalDuration)
    .temperature(originalTemperature)
    .buildAndRegister()

// Laser Type : Duration : Temperature
// CO, CO2    : 90%      : 1.5x
// He, Ne     : 80%      : 2.0x
// Ar, HeNe   : 50%      : 2.5x
// Kr, Xe     : 20%      : 3.0x
// Nd:YAG     : 10%      : 4.0x
for (laser in arrayOf(
    CARBON_MONOXIDE_LASER,
    CARBON_DIOXIDE_LASER))
{
    LASER_CVD_RECIPES.recipeBuilder()
        .input(dust, GalliumTrioxide, 5)
        .input(laser)
        .fluidInputs(Ammonia.getFluid(2000))
        .output(plate, GalliumNitride, 4)
        .output(EMPTY_LASER)
        .fluidOutputs(Steam.getFluid(3000))
        .EUt(VA[LuV].toLong())
        .duration(originalDuration * 0.9)
        .temperature(originalTemperature * 1.5)
        .buildAndRegister()
}
// ...
```