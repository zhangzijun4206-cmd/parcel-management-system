# Parcel Management System

Spring Boot と MyBatis を用いて作成した、シンプルな荷物管理 Web アプリケーションです。  
一覧表示・登録・編集・削除・検索・ページングなど、業務で頻出する基本機能を一通り実装しています。

---

## 機能一覧
- 荷物一覧表示（テーブル形式）
- 新規登録 / 編集 / 削除
- 追跡番号（Tracking No）の一意制御
  - 重複時はエラーページではなく、フォーム上にエラーメッセージを表示
- 追跡番号による検索（LIKE 検索）
- ページング機能（MySQL LIMIT）

---

## 使用技術
- Java 17
- Spring Boot 3.2.1
- MyBatis
- Thymeleaf
- MySQL 8.x
- Maven

---

## プロジェクト構成（主要）

```text
├── controller        // 画面遷移・リクエスト制御
├── service           // 業務ロジック・入力チェック
├── mapper            // MyBatis Mapper Interface
├── resources
│   ├── mapper        // MyBatis XML
│   └── templates
│       └── parcel
│           ├── list.html
│           └── form.html

