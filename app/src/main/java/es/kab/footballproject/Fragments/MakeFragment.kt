package es.kab.footballproject.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import es.kab.footballproject.Activities.MainActivity
import es.kab.footballproject.R
import es.kab.footballproject.databinding.FragmentMakeBinding


class MakeFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentMakeBinding

    companion object{
        private val IMAGES_REQUEST_CODE = 200
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMakeBinding.inflate(inflater)
        (requireActivity() as MainActivity).ToolBarName(getString(R.string.make11))
        binding.floatingCaptura.setOnClickListener(this)

        return binding.root
         }

    override fun onClick(v: View?) {

        if (v!=null){
            when(v.id){
                R.id.floatingCaptura ->{
                    if (ContextCompat.checkSelfPermission(this.requireContext(),Manifest.permission.READ_MEDIA_IMAGES)!= PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(this.requireActivity(), arrayOf(Manifest.permission.READ_MEDIA_IMAGES), IMAGES_REQUEST_CODE)
                    }
                    else{
                    Toast.makeText(context, getString(R.string.captu), Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == IMAGES_REQUEST_CODE) {
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(context, getString(R.string.captu), Toast.LENGTH_SHORT).show()

            }else {
                Toast.makeText(context, getString(R.string.perm), Toast.LENGTH_SHORT).show()
            }
        }
    }



}