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

package com.raywenderlich.chuckyfacts.view.activities

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.raywenderlich.chuckyfacts.App
import com.raywenderlich.chuckyfacts.DetailContract
import com.raywenderlich.chuckyfacts.DetailPresenter
import com.raywenderlich.chuckyfacts.R
import com.raywenderlich.chuckyfacts.entity.Joke
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.toolbar_view_custom_layout.*
import org.jetbrains.anko.toast
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Command

class DetailActivity : BaseActivity() , DetailContract.View {

  companion object{
    const val TAG = "DetailActivity"
  }

  private var presenter : DetailContract.Presenter? = null

  private val navigator: Navigator? by lazy {
    object : Navigator {
      override fun applyCommand(command: Command) {
        if (command is Back) {
          back()
        }
      }

      private fun back() {
        finish()
      }
    }
  }

  private val toolbar by lazy {
    toolbar_toolbar_view
  }

  private val tvId by lazy {
    tv_joke_id_activity_detail
  }

  private val tvJoke by lazy {
    tv_joke_activity_detail
  }


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    presenter = DetailPresenter(this)
  }

  override fun getToolbarInstance(): Toolbar? {
    return toolbar
  }

  override fun showJokeData(id: String, joke: String) {
    tvId.text = id
    tvJoke.text = joke
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    return when(item?.itemId){
      android.R.id.home -> {
        presenter?.backButtonClicked()
        true
      }
      else -> false
    }
  }

  override fun onResume() {
    super.onResume()
    App.INSTANCE.cicerone.navigatorHolder.setNavigator(navigator)
    supportActionBar?.let {
      it.setDisplayHomeAsUpEnabled(true)
    }

    val argument = intent.getParcelableExtra<Joke>("data")
    argument?.let {
      presenter?.onViewCreated(it)
    }
  }

  override fun onPause() {
    super.onPause()
    App.INSTANCE.cicerone.navigatorHolder.removeNavigator()
  }

  override fun showInfoMessage(msg: String) {
    toast(msg)
  }
}