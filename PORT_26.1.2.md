\# Terrain Diffusion MC — Minecraft 26.1.2 Port



This branch is an unofficial working port of `xandergos/terrain-diffusion-mc` for Minecraft/Fabric \*\*26.1.2\*\*.



\## Status



Working:



\* Minecraft \*\*26.1.2\*\*

\* Fabric Loader \*\*0.19.2\*\*

\* Fabric API \*\*0.152.1+26.1.2\*\*

\* Java \*\*25\*\*

\* Windows DirectML build

\* CUDA build tested on NVIDIA GPU

\* Terrain Diffusion world type

\* Vanilla \*\*Customize\*\* button support for Terrain Diffusion world scale settings

\* World generation and model loading



\## Notes



This is not the official upstream release. It is a compatibility port for Minecraft 26.1.2.



For heavily modded clients, allocating around \*\*10–12 GB RAM\*\* may reduce frame drops. Mods that request extra terrain generation, such as Voxy/Voxy World Gen, may increase RAM usage or stutter with Terrain Diffusion.



\## Recommended config



```properties

inference.device=gpu

inference.offload\_models=true

tile\_size=128

```



`tile\_size=256` may generate more terrain per batch, but `128` can feel smoother on larger modpacks.



\## Tags



Known working tag:



```text

terrain-diffusion-26.1.2-working

```



