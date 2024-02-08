package com.example.lesson52.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson52.R
import com.example.lesson52.data.viewmodel.FootballTeamViewModel
import com.example.lesson52.databinding.FragmentRecyclerViewBinding
import com.example.lesson52.ui.adapter.FootballTeamAdapter
import com.example.lesson52.utils.UiState
import com.google.android.material.snackbar.Snackbar

class RecyclerViewFragment : Fragment() {
    private var _binding: FragmentRecyclerViewBinding? = null
    private val binding get() = _binding!!
    private var footballTeamAdapter = FootballTeamAdapter()
    private val viewModel: FootballTeamViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupRecyclerView()
        setupAddTeamButton()

        Log.e("TAG", "onViewCreated: ${viewModel.data.value}")
    }

    private fun setupViewModel() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    Log.e("football", "setupViewModel: Loading")
                    Snackbar.make(requireView(), "Загрузка...", Snackbar.LENGTH_SHORT).show()
                }

                is UiState.Success -> {
                    Log.e("football", "setupViewModel: ${state.data}")
                    footballTeamAdapter.updateData(state.data)
                }

                is UiState.Error -> {
                    Log.e("football", "setupViewModel: Error")
                    Snackbar.make(requireView(), state.message, Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupRecyclerView() = with(binding) {
        rv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = footballTeamAdapter
        }
    }

    private fun setupAddTeamButton() {
        binding.fabAddTeam.setOnClickListener {
            Log.e("navigation", "nonav")
            findNavController().navigate(R.id.action_recyclerViewFragment_to_addFragment)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
