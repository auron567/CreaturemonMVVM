package com.example.creaturemonmvvm.model

import com.example.creaturemonmvvm.R

object AvatarStore {
    val AVATARS: List<Avatar> by lazy {
        mutableListOf<Avatar>().apply {
            add(Avatar(R.drawable.creature_app_whistle_1))
            add(Avatar(R.drawable.creature_bear_sleepy))
            add(Avatar(R.drawable.creature_bird_blue_fly_1))
            add(Avatar(R.drawable.creature_bug_insect_happy))
            add(Avatar(R.drawable.creature_bug_spider))
            add(Avatar(R.drawable.creature_cat_derp))
            add(Avatar(R.drawable.creature_cow_01))
            add(Avatar(R.drawable.creature_dino_01))
            add(Avatar(R.drawable.creature_dog_bone))
            add(Avatar(R.drawable.creature_duck_yellow_1))
            add(Avatar(R.drawable.creature_frog_hungry))
            add(Avatar(R.drawable.creature_head_fox))
            add(Avatar(R.drawable.creature_head_pig))
            add(Avatar(R.drawable.creature_head_tiger))
            add(Avatar(R.drawable.creature_monkey_happy))
            add(Avatar(R.drawable.creature_monster_purple))
            add(Avatar(R.drawable.creature_monster_slug))
            add(Avatar(R.drawable.creature_monster_yeti_1))
            add(Avatar(R.drawable.creature_owl_attack_1))
            add(Avatar(R.drawable.creature_panda_fun))
            add(Avatar(R.drawable.creature_penguin_plane))
            add(Avatar(R.drawable.creature_rat))
            add(Avatar(R.drawable.creature_skunk))
            add(Avatar(R.drawable.creature_square_bunny))
            add(Avatar(R.drawable.creature_wolf_crazy))
        }
    }
}