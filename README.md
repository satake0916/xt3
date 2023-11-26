# xt3

Twitter Clone作成プロジェクトです。

## 開発ことはじめ

### インフラ・バックエンド

```bash
docker-compose up -d --build
./gradlew flywayMigrate
./gradlew bootRun
```

### フロント

```bash
cd front
yarn start
```

## ドキュメント

docs以下で管理しています。