\# Terrain Diffusion MC 26.1.2 CUDA Windows Setup



Known working on:

\- RTX 5070

\- Windows

\- NVIDIA driver 595.79

\- CUDA Toolkit 12.8

\- cuDNN 9.23, CUDA 12.9 bin folder on PATH

\- Java 25

\- ONNX Runtime GPU 1.20.0

\- Minecraft 26.1.2

\- Fabric Loader 0.19.2

\- Fabric API 0.152.1+26.1.2



Required PATH entries:

\- C:\\Program Files\\NVIDIA GPU Computing Toolkit\\CUDA\\v12.8\\bin

\- C:\\Program Files\\NVIDIA\\CUDNN\\v9.23\\bin\\12.9\\x64



Verify:

```powershell

where.exe nvcc

where.exe cudnn64\_9.dll

where.exe cudnn\_ops64\_9.dll

where.exe cudnn\_cnn64\_9.dll

where.exe cublas64\_12.dll

where.exe cudart64\_12.dll

java -version

javac -version

