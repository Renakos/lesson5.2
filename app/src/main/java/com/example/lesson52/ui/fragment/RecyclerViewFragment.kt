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
        checkForTeams()
        setupAddTeamButton()
        setupRecyclerViewClick()
        Log.e("TAG", "onViewCreated: ${viewModel.data.value}")
    }

    private fun setupViewModel() {
        viewModel.data.observe(viewLifecycleOwner) { teams ->
            footballTeamAdapter.updateData(teams)
        }

        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    Snackbar.make(requireView(), "Загрузка...", Snackbar.LENGTH_SHORT).show()
                }

                is UiState.Success -> {
                    footballTeamAdapter.updateData(state.data)

                }

                is UiState.Error -> {
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

    private fun checkForTeams() {
        footballTeamAdapter.updateData(viewModel.allTeams)
    }

    private fun setupAddTeamButton() {
        binding.fabAddTeam.setOnClickListener {
            findNavController().navigate(R.id.action_recyclerViewFragment_to_addFragment)
        }
    }

    private fun setupRecyclerViewClick() {
        footballTeamAdapter.setOnItemLongClickListener(object :
            FootballTeamAdapter.OnItemLongClickListener {
            override fun onItemLongClick(position: Int) {
                val teamToDelete = footballTeamAdapter.footballTeams[position]
                viewModel.deleteTeam(teamToDelete)

            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
