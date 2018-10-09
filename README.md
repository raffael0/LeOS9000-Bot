# LeOS 9000
[![Bless](https://cdn.rawgit.com/LunaGao/BlessYourCodeTag/master/tags/god.svg)](http://lunagao.github.io/BlessYourCodeTag/) 
[![Bless](https://cdn.rawgit.com/LunaGao/BlessYourCodeTag/master/tags/jesus.svg)](http://lunagao.github.io/BlessYourCodeTag/) 
[![Bless](https://cdn.rawgit.com/LunaGao/BlessYourCodeTag/master/tags/allah.svg)](http://lunagao.github.io/BlessYourCodeTag/) 
[![Bless](https://cdn.rawgit.com/LunaGao/BlessYourCodeTag/master/tags/ramen.svg)](http://lunagao.github.io/BlessYourCodeTag/) 
[![](https://forthebadge.com/images/badges/built-with-wordpress.svg)](https://forthebadge.com)
[![](https://forthebadge.com/images/badges/gluten-free.svg)](https://forthebadge.com)

The LeOS9000 Bot features several commands and focuses on speedcubing. It has a cubing only mode which blocks all non cubing related commands from being used. This is especially useful 

[Add the bot to your server](https://discordapp.com/api/oauth2/authorize?client_id=460120329264693258&permissions=121856&scope=bot)

## Interactive Cube
The bot now features interactive cubing commands. You can use ```-cube[number] start``` to spawn a new cube. The number can range from 2-7. You then have to select the cube using ```-select [number]```. This is needed as you can have cubes of multiple sizes. You can only have one cube of every size. After you have selected your cube, you can manipulate it using ```-move [your moves]```. To reset your cube, you can use ```-cube[number] reset```. If you want to delete your cube completely, you can use ```cube[number] stop```. You can get an overview of the different cubes using ```-cube help```. To get some info about your current cubes, use ```-cube info```.

## Global Chat
![Imgur](https://i.imgur.com/4rQ9v5o.png)                                               
The global chat connects all servers that the bot is currently on. To use it, you must have a channel named "#global" on your server. You can connect to the global chat using 
```-global connect```
After that, you can just write messages into the #global channel and the bot will deliver it to all the other servers connected to the global chat! To show all the connected servers, just use 
```-global```
If you wish to disconnect from the global chat, use 
```-global disconnect```
If a server is annoying, use the ```-vk``` command to get a list of all servers and then ```-vk [id of the server that you want to kick]```. This will start a votekick. If other agree with you, the server will get kicked from the global chat.
## The .image and the .web commands
The ```-image``` command gives you an interactive menu to browse all the images on a webpage!
![Imgur](https://i.imgur.com/uSSiAka.png)                                                             
The ```-web``` command gets all text content from a website and displays it as a discord message
[Imgur](https://i.imgur.com/b576YSf.png)                                                                                
Note that these two features are highly experimental and may not always work right, particularly the ```-web``` command

## Scramble Commands
![Imgur](https://i.imgur.com/hdnoU8x.png)                                                                     
Using the -drawscramble command will give you the menu pictured above. When using the -sc[N] command, the bot will provide you with an interactive menu where you can switch between two images that show the full view of the cube. 
![Imgur](https://i.imgur.com/s1z1wio.png)                                                     
It also features dedicated views of subsets, such as f2l. Feel free to play around with the commands yourself!

## Cubing Mode
Using the -cubing command will allow you to change the cubing mode settings of your server. If you turn it on using
```-cubing on```
the bot will only respond to cubing related commands. This excludes the image and the web commands. To turn it off, just use
```-cubing off```

## Feedback, Invite and Github
The ```-feedback``` command allows you to give feedback to the bots developer, who might even respond to your feedback! just use ```-feedback [your feedback]```.
The ```-invite``` command returns a link to the support server and a link to invite the bot to your own server!
The ```-github``` command returns a link to this github repo.
