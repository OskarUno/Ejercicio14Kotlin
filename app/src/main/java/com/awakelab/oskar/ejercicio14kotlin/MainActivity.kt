package com.awakelab.oskar.ejercicio14kotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.awakelab.oskar.ejercicio14kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var saldo = 0
    private var mensaje: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnConfirmar.setOnClickListener {
            when (binding.rGOpciones.checkedRadioButtonId) {
                R.id.rBSaldo -> binding.eTMonto.setText(saldo.toString())
                R.id.rBIngresar -> ingresarSaldo()
                R.id.rBRetirar -> retirarSaldo()
                R.id.rBSalir -> finish()
            }
        }
    }

    private fun ingresarSaldo() {
        val monto = binding.eTMonto.text.toString().toIntOrNull() ?: 0
        if (montoCero(monto)) {
            saldo += monto
            mensaje = "Tu saldo se a agregado exitosamente"
            crearMensaje(mensaje)
            mensaje = "Monto Actual $saldo"
            crearMensaje(mensaje)
            limpiarMonto()
        } else {
            mensaje = "Ingresar monto mayor a $0"
            crearMensaje(mensaje)
        }
    }

    private fun montoCero(monto: Int): Boolean {
        return monto > 0
    }

    private fun retirarSaldo() {
        val monto = binding.eTMonto.text.toString().toIntOrNull() ?: 0
        if (!montoCero(monto)) {
            mensaje = "Monto debe ser mayor a $ 0"
            crearMensaje(mensaje)
        } else if (monto <= saldo) {
            saldo -= monto
            limpiarMonto()
            mensaje = "Retiro exitoso"
            crearMensaje(mensaje)
            mensaje = "Monto Actual $ $saldo"
            crearMensaje(mensaje)
        } else {
            limpiarMonto()
            mensaje = "Monto Actual Insuficiente $ $saldo"
            crearMensaje(mensaje)
        }
    }

    private fun crearMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    private fun limpiarMonto() {
        binding.eTMonto.text.clear()
    }
}