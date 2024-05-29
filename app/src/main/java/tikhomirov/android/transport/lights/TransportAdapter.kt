package tikhomirov.android.transport.lights

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import tikhomirov.android.transport.lights.databinding.TransportItemBinding

// Объявляем тип onDeletePressedListener как функцию, которая принимает объект TransportModel и ничего не возвращает
typealias onDeletePressedListener = (TransportModel) -> Unit

// Класс адаптера для списка транспорта
class TransportAdapter(
    private val context: Context, // Контекст приложения
    private val transportList: List<TransportModel>, // Список транспортных моделей
    private val onDeletePressedListener: onDeletePressedListener // Слушатель события удаления транспорта
) : BaseAdapter() {

    // Возвращает количество элементов в списке
    override fun getCount(): Int {
        return transportList.size
    }

    // Возвращает элемент списка по указанной позиции
    override fun getItem(position: Int): TransportModel {
        return transportList[position]
    }

    // Возвращает идентификатор элемента списка по указанной позиции
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // Возвращает представление элемента списка
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return getDefaultView(position, convertView, parent, false)
    }

    // Возвращает представление элемента списка для выпадающего списка
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return getDefaultView(position, convertView, parent, true)
    }

    // Возвращает стандартное представление элемента списка
    private fun getDefaultView(
        position: Int, // Позиция элемента в списке
        convertView: View?, // Представление элемента списка
        parent: ViewGroup?, // Родительское представление
        isDropDownView: Boolean // Флаг, указывающий, является ли представление для выпадающего списка
    ): View {
        val binding: TransportItemBinding
        val view: View

        // Если представление элемента списка еще не создано
        if (convertView == null) {
            binding = TransportItemBinding.inflate(LayoutInflater.from(context), parent, false)
            view = binding.root
            view.tag = binding
        } else {
            binding = convertView.tag as TransportItemBinding
            view = convertView
        }

        val currentItem = transportList[position]
        // Устанавливаем название транспорта в соответствующее представление
        binding.titleTransport.text = currentItem.name

        // Устанавливаем видимость иконки удаления в зависимости от значения isShowRemoveIcon
        binding.deleteImageView.visibility =
            if (isDropDownView) View.GONE else View.VISIBLE

        // Устанавливаем слушатель нажатия на иконку удаления
        binding.deleteImageView.setOnClickListener {
            onDeletePressedListener.invoke(currentItem)
        }

        // Возвращаем представление элемента списка
        return view
    }
}
