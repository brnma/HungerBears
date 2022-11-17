package com.example.hungerbears

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.hungerbears.databinding.FragmentSelectionBinding
import com.yuyakaido.android.cardstackview.*


class SelectionFragment : Fragment() {

    private var _binding:FragmentSelectionBinding? = null
    private val binding get() = _binding!!

    // private val viewModel: SharedViewModel by activityViewModels()

    private lateinit var manager: CardStackLayoutManager

    private var dummyList = ArrayList<Restaurant>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectionBinding.inflate(inflater, container, false)

        val chipotle = Restaurant()
        chipotle.setItem("Chipotle", R.drawable.chipotle, "10.1 Miles Away", 2.69f)
        val mcdonalds = Restaurant()
        mcdonalds.setItem("Mcdonalds", R.drawable.mcdonalds, "43.2 Miles Away", 1.2f)
        val jollibee = Restaurant()
        jollibee.setItem("Jollibee", R.drawable.jollibee, "1.5 Miles Away", 4.20f)

        dummyList.add(chipotle)
        dummyList.add(mcdonalds)
        dummyList.add(jollibee)
        dummyList.add(chipotle)
        dummyList.add(mcdonalds)
        dummyList.add(jollibee)
        init()
        binding.cardStackView.layoutManager = manager
        binding.cardStackView.itemAnimator = DefaultItemAnimator()
        binding.cardStackView.adapter = CardStackAdapter(requireContext(), dummyList,)

        binding.yesButton.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            binding.cardStackView.swipe()
            binding.yesButton.animate().apply {
                duration = 1000
                rotationXBy(360f)
            }.start()
        }

        binding.redoButton.setOnClickListener{
            binding.cardStackView.rewind()

            binding.redoButton.animate().apply {
                duration = 1000
                rotationBy(-360f)

            }
        }

        binding.noButton.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            binding.cardStackView.swipe()
            binding.noButton.animate().apply {
                duration = 1000
                rotationXBy(360f)
            }.start()
        }

        return binding.root
    }

    private fun init() {
        manager = CardStackLayoutManager(activity as MainActivity, object: CardStackListener{
            override fun onCardDragging(direction: Direction?, ratio: Float) {
            }

            override fun onCardSwiped(direction: Direction?) {
                if (direction == Direction.Left) {
                    binding.noButton.animate().apply {
                        duration = 1000
                        rotationXBy(360f)
                    }.start()
                }

                else if (direction == Direction.Right) {
                    binding.yesButton.animate().apply {
                        duration = 1000
                        rotationXBy(360f)
                    }.start()
                }
            }

            override fun onCardRewound() {
            }

            override fun onCardCanceled() {
            }

            override fun onCardAppeared(view: View?, position: Int) {
            }

            override fun onCardDisappeared(view: View?, position: Int) {
            }

        })
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(0.6f)
        manager.setScaleInterval(0.8f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.FREEDOM)
    }


}