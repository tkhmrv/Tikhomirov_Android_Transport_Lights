package tikhomirov.android.transport.lights

import android.os.Parcel
import android.os.Parcelable

data class TransportModel(
    val name: String,
    val type: String,
    val capacity: String,
    val axleCount: Int,
    val isShowRemoveIcon: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(type)
        parcel.writeString(capacity)
        parcel.writeInt(axleCount)
        parcel.writeByte(if (isShowRemoveIcon) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TransportModel> {
        override fun createFromParcel(parcel: Parcel): TransportModel {
            return TransportModel(parcel)
        }

        override fun newArray(size: Int): Array<TransportModel?> {
            return arrayOfNulls(size)
        }
    }
}
