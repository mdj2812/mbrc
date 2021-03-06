package com.kelsos.mbrc.adapters

import android.app.Activity
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindString
import butterknife.BindView
import butterknife.ButterKnife
import com.kelsos.mbrc.R
import com.kelsos.mbrc.domain.Genre
import com.raizlabs.android.dbflow.list.FlowCursorList
import javax.inject.Inject

class GenreEntryAdapter
@Inject
constructor(context: Activity) : RecyclerView.Adapter<GenreEntryAdapter.ViewHolder>() {
  private var data: FlowCursorList<Genre>? = null
  private var listener: MenuItemSelectedListener? = null
  private val inflater: LayoutInflater

  init {
    inflater = LayoutInflater.from(context)
  }

  fun setMenuItemSelectedListener(listener: MenuItemSelectedListener) {
    this.listener = listener
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = inflater.inflate(R.layout.listitem_single, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val entry = data!!.getItem(position.toLong())
    holder.title.text = if (TextUtils.isEmpty(entry.name)) holder.empty else entry.name

    holder.indicator.setOnClickListener {
      val popupMenu = PopupMenu(it.context, it)
      popupMenu.inflate(R.menu.popup_genre)
      popupMenu.setOnMenuItemClickListener {
        return@setOnMenuItemClickListener listener?.onMenuItemSelected(it, entry) ?: false
      }
      popupMenu.show()
    }

    holder.itemView.setOnClickListener { listener?.onItemClicked(entry) }
  }

  /**
   * Returns the total number of items in the data set hold by the adapter.

   * @return The total number of items in this adapter.
   */
  override fun getItemCount(): Int {
    return data?.count ?: 0
  }

  fun refresh() {
    data?.refresh()
    notifyDataSetChanged()
  }

  interface MenuItemSelectedListener {
    fun onMenuItemSelected(menuItem: MenuItem, entry: Genre) : Boolean

    fun onItemClicked(genre: Genre)
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    @BindView(R.id.line_one) lateinit var title: TextView
    @BindView(R.id.ui_item_context_indicator) lateinit var indicator: LinearLayout
    @BindString(R.string.empty) lateinit var empty: String

    init {
      ButterKnife.bind(this, itemView)
    }
  }

  fun update(cursor: FlowCursorList<Genre>) {
    data = cursor
    notifyDataSetChanged()
  }
}
