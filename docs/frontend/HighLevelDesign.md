# 概念設計

## 採用技術

### プログラミング言語

TypeScript

* フロントは手軽に作りたかったので使い慣れた技術スタックでやるのが基本方針。

### フレームワーク・ライブラリ

React

* UdemyでちょうどReactでTwitterクローンを作るコースがあったので乗っかった。
  * <https://www.udemy.com/course/twitter-clone-using-react-firebase/>

### パッケージ管理

yarn

* 普段使っているnpmより早いらしいので使ってみた。
* まあ大差ない。

### バックエンドとのインターフェース

Open API(typescript-fetch)

* バックと二重定義するのは嫌だったのでOpen API Specドリブンで開発した。
* 通信部分まで生成してほしかったので、typescriptでは物足りない。ブラウザで動かすだけなのでfetchを採用した。

### リンター・フォーマッター

ESLint, Prettier, Stylelint

* ESLintはrecommendedとairbnbを基本に、simple-importを加えたかたち。
* Prettierは設定いらないのが好みなので、全てデフォルト。
* StylelintはCSSの文法が不安なレベルなので、standardを入れて従う方針。

