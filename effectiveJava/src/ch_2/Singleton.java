package ch_2;

/**
 * シングルトンにおける staticファクトリーメソッド
 */
public class Singleton {

    // privateでインスタンス生成
    private static Singleton singleton = new Singleton();

    // クラス外部からインスタンス化できないよう、コンストラクタをprivateで宣言
    private Singleton() {
    }

    // getInstance が staticファクトリーメソッド
    // 「public static」でメソッドを宣言
    public static Singleton getInstance() {
        return singleton;
    }
}

// 以下、呼び出し例
class test {

    private void fun(){

        // Singletonクラスのインスタンス生成不可
        // コンパイルエラー
        // new Singleton();

        // staticファクトリーメソッドを通じて、インスタンス取得
        Singleton.getInstance(); // インスタンスは一つ
    }
}
