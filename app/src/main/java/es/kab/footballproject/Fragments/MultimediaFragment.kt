package es.kab.footballproject.Fragments
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment
import es.kab.footballproject.databinding.FragmentMultimediaBinding

class MultimediaFragment : Fragment() {

    private lateinit var binding: FragmentMultimediaBinding
    private lateinit var videoView: VideoView

    companion object {
        private const val ARG_VIDEO_NAME = "videoName"

        fun newInstance(videoName: String): MultimediaFragment {
            val fragment = MultimediaFragment()
            val args = Bundle()
            args.putString(ARG_VIDEO_NAME, videoName)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMultimediaBinding.inflate(inflater, container, false)
        videoView = binding.videoView
        prepareVideo()
        binding.closeButton.setOnClickListener {
            videoView.stopPlayback()
            parentFragmentManager.popBackStack()
        }
        return binding.root
    }

    private fun prepareVideo() {
        val videoName = arguments?.getString(ARG_VIDEO_NAME) ?: ""
        val id = resources.getIdentifier(videoName, "raw", requireContext().packageName)

        if (id != 0) {
            videoView.setVideoURI(Uri.parse("android.resource://${requireContext().packageName}/$id"))
            val mediaController = MediaController(requireContext())
            mediaController.setAnchorView(videoView)
            videoView.setMediaController(mediaController)
            videoView.start()
        } else {
            Toast.makeText(requireContext(), "Video not found", Toast.LENGTH_SHORT).show()
        }
    }
}
