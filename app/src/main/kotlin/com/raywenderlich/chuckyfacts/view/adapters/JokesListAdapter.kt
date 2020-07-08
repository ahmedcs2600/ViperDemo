/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.chuckyfacts.view.adapters

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.raywenderlich.chuckyfacts.R
import com.raywenderlich.chuckyfacts.entity.Joke

import kotlinx.android.synthetic.main.card_view_custom_layout.view.*

class JokesListAdapter(private var listener: (Joke?) -> Unit, private var dataList: List<Joke>?) : RecyclerView.Adapter<JokesListAdapter.ViewHolder>() {

  // Creating card_view_custom_layout ViewHolder to speed up the performance
  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvId: TextView? = itemView.tv_id_card_view_custom_layout
    val tvJoke: TextView? = itemView.tv_joke_card_view_custom_layout
  }

  override fun getItemCount() = dataList?.size ?: 0

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    // create a new view
    val viewRow = LayoutInflater.from(parent.context).inflate(R.layout.card_view_custom_layout, parent, false)
    return ViewHolder(viewRow)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.tvId?.text = dataList?.let { it[position].id.toString() }
    holder.tvJoke?.text = dataList?.let { Html.fromHtml(it[position].text) }
    holder.itemView?.setOnClickListener { listener(dataList?.get(position)) }
  }

  fun updateData(list: List<Joke>) {
    this.dataList = list
    this.notifyDataSetChanged()
  }
}
