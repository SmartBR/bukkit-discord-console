# bukkit-discord-console: External bukkit console!
This plugin allows you to use commands on your server's console through discord.

# How to install
- Place the plugin [jar](https://github.com/SmartBR/bukkit-discord-console/releases) in your server's plugins folder.
- in ``plugins/bukkit-discord-console/config.yml``:
    - Create an application on https://discord.com/developers/applications.
    - In the Bot category, copy your token and place it in the token parameter.
    - Ready! Turn on the server and check if the plugin has been enabled correctly.

# How to use
- The default prefix is ``_``. Don't forget to put it on before executing any command.
- Run the "ping" command to verify that the bot has been enabled correctly.
- You can mention the bot to get some information.
- Run ``executor <command>`` to run a command at the server console.

# Contribute
- Clone this repository using [Git](https://git-scm.com/downloads).
- Create the project with Gradle (terminal):
```
linux: ./gradlew
windows: gradlew
```
- Submit [Pull Requests](https://www.digitalocean.com/community/tutorials/como-criar-um-pull-request-no-github-pt) to help improve the project.