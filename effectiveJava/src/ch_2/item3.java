package ch_2;

/**
 * 項目3 privateのコンストラクタか enum型でシングルトン特性を強制する
 */
public class item3 {

}

// クラスをシングルトンにする方法は以下。

// public finalのフィールドによるシングルトン
/** public */ class Elvis1 {

    // privateコンストラクタでインスタンスを作成し、publicのフィールドに設定して公開
    public static final Elvis1 INSTANCE = new Elvis1();

    private Elvis1() {
        // ・・・
    }

    public void leaveTheBuilding() {
        // ・・・
    }
}
/**
 * privateのコンストラクタは、public static finalのフィールドである、Elvis.INSTANCEを初期化するために、一度だけ呼ばれる。
 * publicあるいは、protectedのコンストラクタがないことで、Elvisはシングルトンとなる。
 *
 * 上記は、AccessibleObject.setAccessibleメソッドを使って、リフレクションによりそのprivateコンストラクタを呼び出せるため危険がある。
 */


// staticファクトリメソッドによるシングルトン
/** public */ class Elvis2 {

    private static final Elvis2 INSTANCE = new Elvis2();

    private Elvis2() {
        // ・・・
    }

    // privateコンストラクタでインスタンスを生成し、staticファクトリメソッドで返却する
    public static Elvis2 getInstance() {
        return INSTANCE;
    }

    public void leaveTheBuilding() {
        // ・・・
    }
}

// 上記、2つの方法のどちらかを使用して実装されいれるシングルトンのクラスをシリアライズ可能(12章参考)にするには、
// 単に、クラスの宣言にimplements Serializableを追加するだけでは不十分

// シングルトンを保証し続けるには。。。

/** public */ class Elvis3 {

    // transient修飾子でインスタンスフィールドを宣言
    private transient final Elvis3 INSTANCE = new Elvis3();

    private Object readResolve() {
        // 本物のElvisを返却し、Elvisの偽物をガベージコレクタに始末させる
        return INSTANCE;
    }
}
/**
 * 備考
 *
 * transient修飾子
 *
 *  transientを付与すると、該当フィールドは直列化の対象となる。
 *  該当変数を持つクラスを直列化しようとして、NotSerializableExceptionが発生した際にこの修飾子を付与すると例外は発生しなくなる。
 *
 *  直列化(シリアライズ、シリアル化)とは
 *   オブジェクトの内部状態をバイトストリームに変換し、そのバイトストリームから再び元と同一情報のオブジェクトを再現できる機能のこと。
 *   オブジェクトをネットワーク経由で転送し、別マシン上でオブジェクトを複製することもできる。
 *
 *   java.io.Serializable
 *   java.io.Externalizable のいずれかを実装しているクラスが、直列化可能
 *
 *   ObjectOutputStream クラスの writeObject() メソッドで書き出し、
 *   ObjectInputStream クラスの readObject() メソッドで読み込みが可能。
 */

// 単一要素を持つ、enumを宣言しシングルトン
/** public */ enum Elvis4 {

    INSTANCE;

    public void leaveTheBuilding() {
        // ・・・
    }
}
/**
 * 単一要素のenum型は、たいていシングルトンを実装する最善の方法
 *
 * →実務ではあまり用いないかもしれないが、大切。
 */
