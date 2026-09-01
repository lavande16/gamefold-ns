# GameFold NS 3.0 R3 正式基线

该目录冻结 2026-08-30 在 R2 基础上的启动门禁更新。R2 及原始 3.0 基线均保持不变，可独立回滚。

- 用户可见应用名：`GameFold NS`
- 包名：`com.switch.fold.baseline.lite`
- versionName/versionCode：`3.0` / `33647389`
- 构建变体：`mainlineRelease`
- APK：`GameFold-NS-3.0-Baseline-R3.apk`
- APK SHA-256：`b86b00b1f9be145c245f44c70ae772a8f098619e5cc4fa2e377ae04808e75fd0`
- APK 大小：`17,033,148` bytes
- 签名：APK Signature Scheme v2
- 确认日期：`2026-08-30`
- 回滚：`../GameFold-NS-3.0-Mainline-Baseline-R2/GameFold-NS-3.0-Baseline-R2.apk`

## 变更范围

- 启动时先检查 GameFold 当前支持的设备/窗口形态；不支持时保持现有形态门禁，不显示文件授权提示，也不执行游戏资源预检。
- MTK/MediaTek 设备门禁保持原有优先级与行为。
- 设备/形态变化时重新执行门禁路由，支持展开后继续进入正常授权或资源流程。
- R2 已验收的关于页链接、外屏开关素材、布局规则和 `run-as` 关闭状态均原样保留。

## 验证证据

- `:app:testMainlineReleaseUnitTest` 完成；该变体当前无 JVM 单测源码，因此为 `NO-SOURCE`。
- `:app:assembleMainlineRelease`、R8、资源优化和 lint 成功；APK v2 签名验证通过。
- `aapt`：`GameFold NS / 3.0 / 33647389 / com.switch.fold.baseline.lite`。
- vivo `403G8G00BM611V0 / iPA2693` 原位安装成功，未清应用数据。
- `run-as com.switch.fold.baseline.lite id` 返回 `package not debuggable`。
- 设备回读 APK SHA-256 与构建产物一致，逐字节 `cmp=0`。
- 冷启动进入 `GameFoldAssemblyActivity`，进程存活且未出现 `FATAL EXCEPTION`、`AndroidRuntime`、`ActivityNotFound` 或 `Permission Denial`。
- 用户已完成实机测试并确认修复 OK，直接批准标注 R3 基线。

## 使用规则

1. 后续 GameFold NS 主线修改从 3.0 R3 基线继续，并先建立独立候选版本。
2. 本目录 APK、元数据和证据冻结，不覆盖；R2 作为上一验证点保留。
3. GameFold PC、FC/NES 和官网 APK 未随本次基线更新。
