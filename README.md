# 📦 Parcel Management System
（配送管理システム）

## 1. 概要

本プロジェクトは、**Java / Spring Boot / MyBatis** を用いて開発した  
**配送情報の検索および一覧表示を行う Web アプリケーション**です。

実務を意識し、以下の点を重視して設計・実装しています。

- Spring Boot による標準的なプロジェクト構成
- MyBatis を用いた SQL ベースのデータアクセス
- ページネーション処理の実装
- 例外処理の共通化
- Thymeleaf による画面描画

※ 本プロジェクトは学習および就職活動用の個人開発であり、  
**ユーザー認証（ログイン）や権限管理機能は実装していません。**

---

## 2. 使用技術

### バックエンド
- Java 17
- Spring Boot 3.x
- MyBatis

### データベース
- MySQL 8.x

### その他
- Maven
- Thymeleaf
- Git / GitHub

---

## 3. 機能一覧

- 配送情報の検索機能
- 配送情報の一覧表示
- ページネーション対応
- 配送ステータスの Enum 管理
- 業務例外の共通ハンドリング
- エラーページ表示

---

## 4. プロジェクト構成

```text
com.at.zijun
├── controller
│   ├── ParcelPageController
│   └── GlobalExceptionHandler
├── service
├── mapper
│   └── ParcelMapper
├── pojo
│   ├── Parcel
│   └── ParcelStatus
├── dto
│   └── PageResult
└── common
    └── BusinessException
設計方針
Controller / Service / Mapper の責務分離

SQL は MyBatis XML にて管理

例外処理は GlobalExceptionHandler に集約

5. 画面構成（Thymeleaf）
URL	内容
/parcel/form	配送情報検索フォーム
/parcel/list	配送情報一覧表示（ページネーション）
/error/business	業務例外発生時のエラーページ

6. データベース設計（例）
sql
复制代码
CREATE TABLE parcel (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  tracking_no VARCHAR(32) NOT NULL,
  status VARCHAR(20),
  created_at TIMESTAMP
);
7. 起動方法
MySQL にデータベースを作成

application.yaml に接続情報を設定

ParcelApplication を起動

8. 学習・実装ポイント
Spring Boot による設定管理と起動フローの理解

MyBatis を用いた Mapper / XML 分離設計

PageResult によるページネーション設計

例外処理の一元管理による保守性向上

9. 今後の拡張予定
配送情報の登録・更新・削除（CRUD）

REST API 形式への拡張

認証・認可機能の追加（将来的対応）

10. 作成者
氏名：子君

志望職種：Java バックエンドエンジニア

居住地：日本