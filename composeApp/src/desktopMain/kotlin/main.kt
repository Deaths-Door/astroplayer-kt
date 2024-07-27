private const val USE_UI = false
internal const val DIRECTORY = "C:\\Users\\Aarav Aditya Shah\\Documents\\GitHub\\project-codebases\\kotlin\\astroplayer-kt\\assets\\audio"
fun main() {
    when(USE_UI) {
        true -> showUI()
        false -> playAudio()
    }
}