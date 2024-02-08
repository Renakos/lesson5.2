package com.example.lesson52.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lesson52.data.local.room.entities.FootballTeam
import com.example.lesson52.data.viewmodel.FootballTeamViewModel
import com.example.lesson52.databinding.FragmentAddBinding
import com.example.lesson52.utils.UiState
import com.google.android.material.snackbar.Snackbar

class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FootballTeamViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupAddButton()
    }

    private fun setupViewModel() {
        viewModel.uiState.removeObservers(viewLifecycleOwner)
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    Log.e("UiState", "callOnLoading")
                }

                is UiState.Success -> {
                    Log.e("UiState", "FixCall")
                }

                is UiState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Snackbar.make(requireView(), state.message, Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun setupAddButton() {
        binding.btnAdd.setOnClickListener {
            val teamName = binding.etTeamName.text.toString()
            val players = binding.etPlayers.text.toString().split(",").toSet()
            val amountOfPlayers = players.size
            val footballTeam = FootballTeam(0,teamName, amountOfPlayers, players)
            if (binding.etTeamName.text.isEmpty() || binding.etPlayers.text.isEmpty()) {
                Log.e("Handle", "setupAddButton: teamName or players is null")
                Snackbar.make(
                    requireView(),
                    "Не введено имя команды или имена игроков команды",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                binding.progressBar.visibility = View.VISIBLE
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.progressBar.visibility = View.GONE
                    viewModel.addData(footballTeam)
                    findNavController().navigateUp()
                }, 2000)
            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
