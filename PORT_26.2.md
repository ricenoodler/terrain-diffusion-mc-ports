\# Terrain Diffusion MC 26.2 Port



Unofficial port of Terrain Diffusion MC to Minecraft/Fabric 26.2.



\## Status



Working build variants:

\- CPU

\- Windows DirectML

\- CUDA



\## Tested setup



\- Minecraft 26.2

\- Fabric Loader 0.19.3

\- Fabric API 0.152.2+26.2

\- Fabric Loom 1.17-SNAPSHOT / Loom 1.17.12

\- Gradle 9.5.1

\- Java 25



\## Main porting changes



\- Updated Minecraft version from 26.1.2 to 26.2.

\- Updated Fabric Loader from 0.19.2 to 0.19.3.

\- Updated Fabric API from 0.152.1+26.1.2 to 0.152.2+26.2.

\- Updated Fabric Loom from 1.16-SNAPSHOT to 1.17-SNAPSHOT.

\- Updated Gradle wrapper to 9.5.1.

\- Updated `TerrainDiffusionDensityFunction` for the 26.2 `DensityFunction` API by replacing `mapAll` with `mapChildren`.

\- Updated screen navigation for 26.2 by changing `minecraft.setScreen(...)` to `minecraft.gui.setScreen(...)`.



\## Notes



This is a base 26.2 compatibility port. New 26.2 biome integration, such as Sulfur Caves, is not yet guaranteed and should be tested separately.

