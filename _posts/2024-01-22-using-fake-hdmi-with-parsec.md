---
layout: post
title: Stream Games from a Headless System with a Dummy HDMI Adapter
comments: true
---

# Stream Games from a Headless System with a Dummy HDMI Adapter

Streaming games from a headless system can be a fantastic way to enjoy high-end gaming on any device. My personal favorite tool for the job is [Parsec][parsec], but any similar service should work the same ([Steam Link][steam-link] and [Moonlight][moonlight] come to mind). In this blog post, we'll walk you through the steps to set up your headless system to for game streaming.

## What You'll Need:

1. **A Headless System**: This is your main gaming computer without a monitor.
2. **Parsec Software**: Free to download and use. Any alternative you prefer is fine. Steam has built in streaming, for example.
3. **A Dummy HDMI Adapter**: This simulates a display and tricks your system into outputting a video signal. I use [this one][dummy-hdmi].
4. **A Secondary Device**: Any device where you want to stream the games, such as a laptop, tablet, or even a smartphone.
5. **A Stable Internet Connection**: Essential for smooth streaming.

## Step 1: Setting Up Your Headless System

First, ensure that your headless system is up and running. Install your favorite games and make sure they are working properly. This system should be powerful enough to handle the games you intend to play.

## Step 2: Using a [Dummy HDMI Adapter][dummy-hdmi]

Plug the [dummy HDMI adapter][dummy-hdmi] into your headless system. This adapter is crucial as it fools the GPU into thinking a monitor is connected, allowing it to render games. Without it, most GPUs won't output a video signal, making streaming impossible. If you've ever tried to use Parsec with a headless system you probably only got a blank screen even though it works fine while a monitor is connected. The [dummy HDMI adapter][dummy-hdmi] fixes this issue.

## Step 3: Installing Parsec

Download and install Parsec on both your headless system and the device you'll be streaming to. Parsec is available for all major platforms _except_ iOS. If you plan to use an iPhone or iPad, consider using Steam Link instead. It is supported on iOS. If that is your plan, just make sure steam is installed and running on both systems.

## Step 4: Configuring Parsec on Your Headless System

After installing Parsec, open it and create an account or log in. In the settings, you’ll want to adjust the host settings to optimize for performance. This includes setting the resolution, which should match the dummy HDMI adapter's resolution, and the bandwidth. These settings are largely dependant on your headless systems hardware (lower res for a less powerful system) and network (local and internet). Lowering the bandwidth reduces network usage at the cost of more visual artifacts in the streamed game.

## Step 5: Connecting to Your Headless System

On your secondary device, open Parsec and log in with the same account. Your headless system should appear as an available computer to connect to. Click on it to initiate the connection.

## Step 6: Enjoy Gaming

Once connected, you should see your headless system's desktop. From here, you can open and play your games as if you were sitting right in front of the system.

## Tips for the Best Streaming Experience

- **Network Quality**: A wired connection is preferable for both the headless system and the secondary device. If that's not possible, ensure a strong Wi-Fi signal.
- **Optimize Settings**: Experiment with Parsec's settings to find the best balance between quality and performance based on your network speed.
- **Update Drivers**: Keep your GPU drivers up to date on your headless system for the best performance.
- **Security**: Make sure your headless system is secure, especially since it's accessible remotely.

## Conclusion

Streaming games from a headless system using Parsec and a dummy HDMI adapter is a fantastic way to enjoy your gaming library anywhere in your home or even on the go. With the right setup and a bit of tweaking, you can turn any device into a powerful gaming machine, leveraging the power of your main system.

Remember, the quality of your experience will largely depend on the capabilities of your headless system and the stability of your internet connection. Happy gaming! 🎮🌐

[parsec]: https://parsec.app/ "Parsec"
[steam-link]: https://store.steampowered.com/app/353380/Steam_Link/ "Steam link"
[moonlight]: https://moonlight-stream.org/ "Moonlight"
[dummy-hdmi]: https://amzn.to/494L5vy "dummy HDMI adapter"
