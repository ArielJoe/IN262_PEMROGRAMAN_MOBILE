package com.example.project5

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.project5.databinding.ActivityMainBinding
import com.example.project5.entity.Department

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private var grantAdapter: ArrayAdapter<CharSequence>? = null
    private var departmentAdapter: ArrayAdapter<Department>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View? = binding.root
        setContentView(view)

        binding.btnSubmit.setOnClickListener { event ->
            this.buttonClick()
        }
        binding.spGrant.adapter = getGrantAdapter(R.array.grants)
        binding.spDepartment.adapter = getDepartmentAdapter()
    }

    private fun buttonClick() {
        val name = binding.etName.text.toString()
        val gender = if (binding.rbMale.isChecked) {
            binding.rbMale.text.toString()
        } else {
            binding.rbFemale.text.toString()
        }
        val grant = binding.spGrant.selectedItem.toString()
        val department = binding.spDepartment.selectedItem as Department
        Toast.makeText(this, "$name $gender $grant $department", Toast.LENGTH_LONG).show()
    }

    private fun getGrantAdapter(name: Int): ArrayAdapter<CharSequence>? {
        if (grantAdapter == null) {
            grantAdapter = ArrayAdapter.createFromResource(
                this,
                name,
                android.R.layout.simple_spinner_item
            )
            grantAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        return grantAdapter
    }

    private fun getDepartmentAdapter(): ArrayAdapter<Department>? {
        if (departmentAdapter == null) {
            departmentAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                getDepartments()
            )
            departmentAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        return departmentAdapter
    }

    private fun getDepartments(): List<Department> {
        val departments = listOf(
            Department("72", "S1 Teknik Informatika"),
            Department("73", "S1 Sistem Informasi"),
            Department("79", "S2 Ilmu Komputer")
        )
        return departments
    }
}
