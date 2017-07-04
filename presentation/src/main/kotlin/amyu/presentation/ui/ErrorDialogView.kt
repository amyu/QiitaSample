package amyu.presentation.ui

import amyu.presentation.domain.exception.NetworkErrorException
import android.app.Activity
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AlertDialog
import java.io.IOException


interface ErrorDialogView {
    fun getActivity(): FragmentActivity
    fun showErrorDialog(e: Throwable) {
        when (e) {
            is NetworkErrorException -> showNetworkErrorDialog(e)
            is IOException -> showIOErrorDialog()
            else -> showUnknownErrorDialog()
        }
    }

    private fun showNetworkErrorDialog(e: NetworkErrorException) {
        when (e.code) {
            0 -> showClientErrorDialog(e.errorMessage)
            401 -> showUnauthorizedErrorDialog(e.errorMessage)
            400, in 402..499 -> showClientErrorDialog(e.errorMessage)
            500 -> showInternalServerErrorDialog(e.errorMessage)
            else -> showUnknownErrorDialog()
        }
    }

    private fun showUnauthorizedErrorDialog(errorMessage: String?) {
        AlertDialog.Builder(getActivity()).apply {
            setTitle("認証エラー")
            setMessage(errorMessage ?: "ログインをやり直してください")
            setPositiveButton("ok", null)
        }.show()
    }

    private fun showClientErrorDialog(errorMessage: String?) {
        AlertDialog.Builder(getActivity()).apply {
            setTitle("通信エラー")
            setMessage(errorMessage ?: "何かが間違ってるので直してください")
            setPositiveButton("ok", null)
        }.show()
    }

    private fun showInternalServerErrorDialog(errorMessage: String?) {
        AlertDialog.Builder(getActivity()).apply {
            setTitle("サーバエラー")
            setMessage(errorMessage ?: "サーバがだめっぽい")
            setPositiveButton("ok", null)
        }.show()
    }

    private fun showIOErrorDialog() {
        AlertDialog.Builder(getActivity()).apply {
            setTitle("ファイルエラー")
            setMessage("何かしらファイルの書き込みとかにエラーが起きました")
            setPositiveButton("ok", null)
        }.show()
    }

    private fun showUnknownErrorDialog(errorMessage: String? = null) {
        AlertDialog.Builder(getActivity()).apply {
            setTitle("予期せぬエラー")
            setMessage(errorMessage ?: "よくわかりません")
            setPositiveButton("ok", null)
        }.show()
    }
}