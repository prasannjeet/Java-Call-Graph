graph [
    comment "This is a simple graph"
    directed 1
    id 11
    label "GML By Prasannjeet Singh"
    node [
        id 0
        label "pl.art.lach.mateusz.javaopenchess.server.Console.readString"
        graphics [ w 374.0 type "roundrectangle" fill "#87E97E" ]
    ]
    node [
        id 1
        label "pl.art.lach.mateusz.javaopenchess.utils.MD5.encrypt"
        graphics [ w 326.0 type "roundrectangle" fill "#B0B8B9" ]
    ]
    node [
        id 2
        label "pl.art.lach.mateusz.javaopenchess.server.Console.main"
        graphics [ w 338.0 type "roundrectangle" fill "#2DEB6D" ]
    ]
    node [
        id 3
        label "pl.art.lach.mateusz.javaopenchess.server.Server.newTable"
        graphics [ w 356.0 type "roundrectangle" fill "#AE1F24" ]
    ]
    node [
        id 4
        label "pl.art.lach.mateusz.javaopenchess.server.Table.getClientPlayer2"
        graphics [ w 398.0 type "roundrectangle" fill "#E14616" ]
    ]
    node [
        id 5
        label "pl.art.lach.mateusz.javaopenchess.server.Table.getClientPlayer1"
        graphics [ w 398.0 type "roundrectangle" fill "#14BC5E" ]
    ]
    node [
        id 6
        label "pl.art.lach.mateusz.javaopenchess.server.Server.print"
        graphics [ w 338.0 type "roundrectangle" fill "#D9C6E8" ]
    ]
    edge [
        source 2
        target 0
        graphics [width 1 type "line" fill "#0000FF" arrow "last"]
    ]
    edge [
        source 2
        target 3
        graphics [width 1 type "line" fill "#0000FF" arrow "last"]
    ]
    edge [
        source 2
        target 5
        graphics [width 1 type "line" fill "#0000FF" arrow "last"]
    ]
    edge [
        source 2
        target 4
        graphics [width 1 type "line" fill "#0000FF" arrow "last"]
    ]
    edge [
        source 2
        target 1
        graphics [width 1 type "line" fill "#0000FF" arrow "last"]
    ]
    edge [
        source 3
        target 6
        graphics [width 1 type "line" fill "#0000FF" arrow "last"]
    ]
]