package tikhomirov.android.transport.lights

import android.os.Parcel
import android.os.Parcelable
import tikhomirov.android.transport.lights.TransportModel

data class State(
    val dataTransports: MutableList<TransportModel> = mutableListOf(),
    val transportViewInfoBackgroundColor: Int = 0,
    val isAddDialogShowing: Boolean = false,
    val isDeleteDialogShowing: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(TransportModel.CREATOR) ?: mutableListOf(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(dataTransports)
        parcel.writeInt(transportViewInfoBackgroundColor)
        parcel.writeByte(if (isAddDialogShowing) 1 else 0)
        parcel.writeByte(if (isDeleteDialogShowing) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<State> {
            override fun createFromParcel(parcel: Parcel): State {
                return State(parcel)
            }

            override fun newArray(size: Int): Array<State?> {
                return arrayOfNulls(size)
            }
        }
    }
}
