package kg.nurtelecom.nur_text_recognizer.photo_capture

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.design2.chili2.extensions.loadImageFormFilePath
import kg.nurtelecom.nur_text_recognizer.databinding.TextRecognizerFragmentPhotoConfirmationBinding

class PhotoConfirmationFragment : Fragment() {

    private val photoUri: Uri? by lazy {
        arguments?.getParcelable(ARG_FILE_URI) as? Uri
    }

    private val shouldRecognizeOnRetry: Boolean by lazy {
        arguments?.getBoolean(ARG_SHOULD_RECOGNIZE_ON_RETRY) ?: false
    }

    private val confirmationLabel: ScreenLabels? by lazy {
        arguments?.getSerializable(ARG_CONFIRMATION_LABELS) as? ScreenLabels
    }

    private var _vb: TextRecognizerFragmentPhotoConfirmationBinding? = null
    private val vb: TextRecognizerFragmentPhotoConfirmationBinding
        get() = _vb!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _vb = TextRecognizerFragmentPhotoConfirmationBinding.inflate(inflater, container, false)
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoUri?.path?.let {
            vb.ivPhoto.loadImageFormFilePath(it)
        }
        vb.btnSecondary.setOnClickListener {
            (requireActivity() as PhotoRecognizerActivityCallback).openCameraFragment(shouldRecognizeOnRetry)
        }
        vb.btnPrimary.setOnClickListener {
            vb.btnPrimary.setIsLoading(true)
            vb.btnSecondary.isEnabled = false
            photoUri?.let {(requireActivity() as PhotoRecognizerActivityCallback).onPhotoConfirmed(it)}
        }
        setupOverlayLabels(confirmationLabel)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
    }

    private fun setupOverlayLabels(screenLabels: ScreenLabels?) {
        vb.apply {
            screenLabels?.description?.let { tvHint.text = it }
        }
    }

    companion object {
        const val ARG_FILE_URI = "file_uri"
        const val ARG_SHOULD_RECOGNIZE_ON_RETRY = "should_recognize_on_retry"
        const val ARG_CONFIRMATION_LABELS = "ARG_CONFIRMATION_LABELS"
    }
}