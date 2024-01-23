---
layout: post
title: Redundant Pi-holes For Your Home Network
comments: true
---

This should be a simple to follow guide for taking two Raspberry Pis and turning them into a redundant pihole cluster. These instructions show you how to set up two Pi-holes such that only one (the primary) is receiving DNS requests at a time. If the primary fails, keepalived is used to automatically fail over all DNS requests to the secondary Pi-hole until the primary Pi-hole comes back online.

## Hardware Requirements

* 2x Raspberry Pis. Nearly any model will do but I've done this successfully with the 3, 4, and Zero.
  * [Raspberry Pi 4 (2GB)][pi4]
    * [SD Card][sd] Required: The kit above does not include and SD Card.
  * [Raspberry Pi Zero W][pi0]
    * [USB Ethernet][usb-eth] _Optional_: You only need this if you insist on ethernet (I run it over wifi). 10/100M is plenty for a pihole. Each DNS request will be less than 500 bytes so you would need to exceed 100,000 requests/second to saturate this ethernet adapter. My pihole with ~30 clients only sees ~65,000 requests _in 24 hours_.
    * This kit comes with an sd card.
    
## Software Used

* [Raspberry PI OS][rpos]
* [Pi-hole][pihole]
* [Gravity Sync][gravity-sync]
* [keepalived][keepalived]

## Overview

There are 5 main steps to get your redundant Pi-holes set up:

1. Install Raspberry Pi OS on each Raspberry Pi
2. Install Pi-hole on each Raspberry Pi 
3. Install Gravity Sync on each Raspberry Pi
4. Install keepalived on each Raspberry Pi
5. Point devices on your network to use the Pi-hole cluster as DNS

## Install Raspberry Pi OS

This process will vary slightly depending on the model you have. As a general rule I would recommend Raspberry Pi OS Lite since the main UI you'll interact with is the web based Pi-hole dashboard. There is no need for a desktop environment. However, if you prefer, the desktop environment won't get in the way of anything and is fine to use.

I'm also assuming you have followed the instructions in your Raspberry Pi kit at least up to installing the OS.

If you have the [Raspberry Pi Zero kit I referenced above][pi0], the SD Card comes preloaded with [NOOBS][noobs] to make installing your preferred OS simple. Just attach a monitor/keyboard/mouse and boot it up. You'll be greeted with a GUI instructing you on how to install an OS. Select Raspberry Pi OS Lite (32 or 64 bit works equally well) and follow the on screen prompts.

For the Rapsberry Pi 4 kit with the blank SD card you will need a computer (windows/linux/mac) and some way to connect the micro sd card (I use [this][microsd1] or [this][microsd2]). Once connected to a computer, use [Raspberry Pi Imager][rpi] to install Rapberry Pi OS Lite.

I also recommend installing an open ssh server so you can do the rest of this work over SSH instead of requiring that a keyboard and monitor is attached at all times.

```bash
sudo apt install -y openssh-server
sudo systemctl enable ssh
sudo systemctl start ssh
```

SSH might already be installed and running. Running the above commands won't hurt anything if that's the case.

Now you can connect to either Pi with SSH:

```bash
ssh pi@<local ip address> # find the ip address with ifconfig if you are unsure
```

The default password is raspberry. Change it.

You will also need to reserve the IP of each Pi in your router's DHCP settings (this varies a lot between different routers) or configure the Pis with a static IP address outside the range of the DHCP server's pool.

## Install Pi-hole

Run:

```
curl -sSL https://install.pi-hole.net | bash
```

This will start an automated install process that will ask you a handful of questions to get everything configured correctly. If you are unsure, the defaults are probably fine.

At this point you will want to decide which Pi-hole will be the primary server and which will be the secondary. In my setup, I chose the Pi at 192.168.86.20 as the primary and the Pi at 192.168.86.19 as the secondary.

[pi4]: https://amzn.to/2Y4w0Yh "Raspberry Pi 4"
[pi3]: https://amzn.to/39Sg80E "Raspberry Pi 3"
[pi0]: https://amzn.to/3p92bBD "Raspberry Pi Zero W"
[usb-eth]: https://amzn.to/35ZPsd9 "USB Ethernet"
[sd]: https://amzn.to/2Mgx5tc "SD Card"
[rpos]: https://www.raspberrypi.org/software/ "Raspberry Pi OS"
[pihole]: https://pi-hole.net/ "Pi-hole"
[gravity-sync]: https://github.com/vmstan/gravity-sync "Gravity Sync"
[keepalived]: https://github.com/acassen/keepalived "keepalived"
[noobs]: https://www.raspberrypi.org/documentation/installation/noobs.md "NOOBS"
[microsd1]: https://amzn.to/3sRGaJR "USB-C Micro sd adapter"
[microsd2]: https://amzn.to/2Y1WbyK "USB-A Micro sd adapter"
[rpi]: https://www.raspberrypi.org/software/ "Raspberry PI Imager"
