package com.example.authentification_cloud
//import androidmads.library.qrgenearator.QRGContents
//import androidmads.library.qrgenearator.QRGEncoder

//import com.google.zxing.WriterException

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.authentification_cloud.databinding.FragmentDetailsReservationBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.fragment_login.*


class Details_ReservationFragment : Fragment() {

    private lateinit var binding: FragmentDetailsReservationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDetailsReservationBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailsReservationBinding.inflate(layoutInflater)
        val view = binding.root
        binding.numReservation.text = "Reservation ${reservationList!!.get(reservationPosition).numReservation}"

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var qrCodeIV:  ImageView = binding.idIVQrcode
        val writer = QRCodeWriter()
        try {
            val bitMatrix = writer.encode("context", BarcodeFormat.QR_CODE, 512, 512)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }

            qrCodeIV.setImageBitmap(bmp)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
        //val manager = getSystemService(WINDOW_SERVICE) as WindowManager?

        // initializing a variable for default display.

        // initializing a variable for default display.
        // val display: Display = manager!!.defaultDisplay

        // creating a variable for point which
        // is to be displayed in QR Code.

        // creating a variable for point which
        // is to be displayed in QR Code.
        val point = Point()
        //display.getSize(point)

        // getting width and
        // height of a point

        // getting width and
        // height of a point
        val width: Int = point.x
        val height: Int = point.y

        // generating dimension from width and height.

        // generating dimension from width and height.
        var dimen = if (width < height) width else height
        //    dimen = dimen * 3 / 4
    }}
// setting this dimensions inside our qr code
// encoder to generate our qr code.

// setting this dimensions inside our qr code
// encoder to generate our qr code.
//  qrgEncoder = QRGEncoder(dataEdt.getText().toString(), null, QRGContents.Type.TEXT, dimen)
//      try {
// getting our qrcode in the form of bitmap.
//    bitmap = qrgEncoder.encodeAsBitmap()
// the bitmap is set inside our image
// view using .setimagebitmap method.
//  qrCodeIV.setImageBitmap(bitmap)
//  } catch (e: WriterException) {
// this method is called for
// exception handling.
//       Log.e("Tag", e.toString())
// }
// }



//}