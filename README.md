# Tamago-Tweaks

A small **client-side Fabric mod** that automates bonemeal usage on moss and cleans up unwanted plants that grow from it.

This mod adjusts your aim and performs the correct click action automatically while you hold bone meal.

---

## 🎥 Demo Video

https://youtu.be/I0qHqRNGK9A

---

## ✨ Features

* Automatically aims at **Moss Block** and right-clicks with bone meal
* Automatically breaks:

  * Moss Carpet
  * Azalea
  * Flowering Azalea
  * Grass
  * Tall Grass
* Repeats the cycle continuously for efficient moss farming
* Fully **client-side** (no server mod required)
* Toggle ON/OFF in-game
* Visible in **Mod Menu**
* Custom mod icon

---

## 🎮 How it works

1. Hold **Bone Meal**
2. Look at a Moss Block
3. The mod will:

   * Use bone meal on moss
   * Remove unwanted plants
   * Re-aim at moss
   * Repeat automatically

You do **not** need to spam click. Just keep aiming at the moss.

---

## ⌨ Keybind

The toggle key can be changed from:

**Options → Controls → Key Binds → Moss Use Helper**

---

## Requirements

* Minecraft **1.19.4**
* Fabric Loader
* Fabric API
* Mod Menu (optional, for settings screen)

---

## Installation

1. Build the mod or download the jar
2. Put it into your `mods` folder
3. Launch Minecraft with Fabric

---

## Technical Notes

* Implemented using a `MinecraftClient` tick mixin
* Uses client interaction manager to simulate correct click actions
* Does not modify server behavior
* Safe to use in any world or server (client-side only)

---

## License

MIT License

---

## Author

Tamago0314
