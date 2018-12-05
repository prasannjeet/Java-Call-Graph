Creator "JGraphT GML Exporter"
Version 1
graph
[
	label ""
	directed 1
	node
	[
		id 1
		label "fr.rhaz.os.Console"
	]
	node
	[
		id 2
		label "fr.rhaz.os.logging.ConsoleLogger"
	]
	node
	[
		id 3
		label "fr.rhaz.os.Utils"
	]
	node
	[
		id 4
		label "fr.rhaz.os.commands.CommandManager"
	]
	node
	[
		id 5
		label "fr.rhaz.os.OS"
	]
	node
	[
		id 6
		label "fr.rhaz.os.logging.Logger"
	]
	node
	[
		id 7
		label "fr.rhaz.os.Session"
	]
	node
	[
		id 8
		label "fr.rhaz.os.commands.users.User"
	]
	node
	[
		id 9
		label "fr.rhaz.os.commands.users.CommandSender"
	]
	node
	[
		id 10
		label "fr.rhaz.os.plugins.PluginManager"
	]
	node
	[
		id 11
		label "fr.rhaz.os.plugins.PluginRunnable"
	]
	node
	[
		id 12
		label "fr.rhaz.events.EventManager"
	]
	node
	[
		id 13
		label "fr.rhaz.os.commands.Command"
	]
	edge
	[
		id 1
		source 1
		target 2
	]
	edge
	[
		id 2
		source 1
		target 5
	]
	edge
	[
		id 3
		source 1
		target 6
	]
	edge
	[
		id 4
		source 2
		target 1
	]
	edge
	[
		id 5
		source 2
		target 6
	]
	edge
	[
		id 6
		source 10
		target 11
	]
	edge
	[
		id 7
		source 10
		target 5
	]
	edge
	[
		id 8
		source 10
		target 12
	]
	edge
	[
		id 9
		source 4
		target 3
	]
	edge
	[
		id 10
		source 4
		target 13
	]
	edge
	[
		id 11
		source 11
		target 10
	]
	edge
	[
		id 12
		source 11
		target 5
	]
	edge
	[
		id 13
		source 11
		target 12
	]
	edge
	[
		id 14
		source 5
		target 1
	]
	edge
	[
		id 15
		source 5
		target 10
	]
	edge
	[
		id 16
		source 5
		target 6
	]
	edge
	[
		id 17
		source 5
		target 12
	]
	edge
	[
		id 18
		source 13
		target 3
	]
]
