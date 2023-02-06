# processing-playground

- [processing/processing4: Processing 4.x releases for Java 17](https://github.com/processing/processing4)

## VSCodeのおすすめ拡張

- [Processing Language - Visual Studio Marketplace](https://marketplace.visualstudio.com/items?itemName=Tobiah.language-pde)
- [Processing Formatter - Visual Studio Marketplace](https://marketplace.visualstudio.com/items?itemName=millennIumAMbiguity.processing-formatter)

## ビルドタスクの設定

以下を設定すると**Ctrl + Shift + B**でビルドできる。

```json
{
  // See https://go.microsoft.com/fwlink/?LinkId=733558
  // for the documentation about the tasks.json format
  "version": "2.0.0",
  "tasks": [
    {
      "label": "Run",
      "command": "C:\\processing-4.1.2-windows-x64\\processing-4.1.2\\processing-java.exe", // <-- ここはよしなに変更
      "type": "process",
      "args": [
        "--force",
        "--sketch=${fileDirname}",
        "--run"
      ],
...

```

それはそうと、processingはプログラムのファイル名とディレクトリ名は一致させる必要があるらしい。

## その他メモ

- [Reference / Processing.org](https://processing.org/reference)
  - 公式リファレンス
- [Processingでゼロから学ぶプログラミング・ビジュアルアート - cocopon.me](https://cocopon.me/zero-pde/)
  - わかりやすい

## ちなみに

試しにScalaで書いたら動いた
