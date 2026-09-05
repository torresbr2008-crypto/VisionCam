# 📱 VisionCam — Advanced Camera Control Application

Um aplicativo Android com sistema avançado de câmera, painel flutuante movimentável, captura de imagens, galeria integrada e controle de zoom/movimento.

## 🎯 Funcionalidades Principais

- **Câmeras Dual** — Frontal e traseira com visualização ao vivo
- **Painel Flutuante** — Movimentável, minimizável, com controle total
- **Captura de Imagens** — Salvar fotos em tempo real
- **Galeria Integrada** — Carregar e exibir imagens
- **Controle Avançado** — Zoom +/-, movimento em X/Y
- **Permissões de Sistema** — Câmera, armazenamento, sobreposição

## 📁 Estrutura do Projeto

```
VisionCam/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/visioncam/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── FloatingPanelService.kt
│   │   │   │   ├── CameraManager.kt
│   │   │   │   ├── ImageController.kt
│   │   │   │   └── PermissionManager.kt
│   ��   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   ├── floating_panel.xml
│   │   │   │   │   └── control_panel.xml
│   │   │   │   ├── values/
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   └── dimens.xml
│   │   │   │   └── drawable/
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   ├── build.gradle
│   └── proguard-rules.pro
├── build.gradle
├── settings.gradle
└── gradle.properties
```

## 🔧 Requisitos

- Android 8.0+ (API 26)
- Camera2 API
- Java 11+

## 📦 Instalação

1. Clone o repositório
2. Abra no Android Studio
3. Configure o SDK
4. Build → Build APK
5. Instale no dispositivo

## 📄 Licença

MIT License

## 👤 Desenvolvedor

**torresbr2008-crypto**

---

**Status:** Em Desenvolvimento 🚀
