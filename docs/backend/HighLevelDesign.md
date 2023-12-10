# 概念設計

## 採用技術

### プログラミング言語

Kotlin

* このプロジェクトを始めたきっかけ。Kotlinを書きたくなった。
* もっと言えばJavaよりパワフルであることを実感したかったから。

### フレームワーク

Spring

* Spring自体の経験があり、認証周りなど自分の弱い部分を任せたかったから。
* 個人開発にしてはごついフレームワークなのでKtorも使いたかったが、KotlinもNew Tech for Meなのでここは使い慣れている方にした。

### ビルドツール

Gradle

* Kotlinと組み合わせるとGradle Kotlin DSLで統一感があって好み。
* Open APIの生成も一手に任せられるので相性が良いと思い採用した。

### データベース

MySQL

* Twitterが以前MySQLを使用していたという記事を見たため乗っかった。
* リプライ周りで必要になるであろう再帰クエリもサポートされているので文句はなかった。

### ORマッパー

Exposed

* Kotlinを活かすためにきちんとNull安全を保てるORマッパーとして採用した。
* 以前KotlinプロジェクトでMyBatisを使用したが全部Nullableになって気持ち悪かったというのが大きい。

### DBマイグレーション

Flyway

* DB関連ではExposedがNew Tech for Meなので、ここも経験のあるツールに任せた。

### フロントとのインターフェース

OpenAPI(kotlin-spring)

* フロントと二重で型定義をしたくなかったため、OpenAPI Specドリブンで開発することにした。

### リンター・フォーマッター

detekt + detekt-formatting

* ktlintの細かい設定ができない点が好みだったので採用しようとしたが、IntelliJプラグイン更新が止まっているように見えたので不採用。
* detektは公式がプラグインを用意しており、ktlintのルールをdetekt-formattingで再現できるとのことだったので採用。
