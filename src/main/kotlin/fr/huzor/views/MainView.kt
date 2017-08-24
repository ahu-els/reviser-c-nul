package fr.huzor.views

import fr.huzor.Person
import fr.huzor.Styles
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.control.Alert.AlertType.INFORMATION
import javafx.scene.control.TabPane
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.text.FontWeight
import javafx.stage.FileChooser
import tornadofx.*
import java.time.LocalDate
import java.time.Period
import tornadofx.getValue
import tornadofx.setValue
import java.io.File
import javax.json.JsonObject

class MainView : View() {
    override val root = HBox()
    //val myOtherView: MyOtherView by inject()
    val myOtherView: MyView by inject()
    init {
        title = "titre my other view"
        with(root){
            label (title) {
                addClass(heading)
            }
        }
    }
    /*override val root = borderpane {
        addClass(Styles.welcomeScreen)
        top {
            stackpane {
                label(title).addClass(Styles.heading)
            }
        }
        center {
            stackpane {
                addClass(Styles.content)
                button("Click me") {
                    setOnAction {
                        alert(INFORMATION, "Well done!", "You clicked me!")
                    }
                }
            }
        }
    }*/
}
/*class MasterView: View() {
    val topView: TopView by inject()
    val bottomView: BottomView by inject()

    override val root = borderpane {
        top = topView.root
        bottom = bottomView.root
    }
}

class TopView: View() {
    override val root = label("Top View")
}

class BottomView: View() {
    override val root = label("Bottom View")
}*/
class MyView: View() {

    var trads = listOf (
            Traduction("étudier", "study", "estudiar", "shui", 0.5f),
            Traduction("amour", "love", "amor", "ai", 0.75f),
            Traduction("frère", "brother", "hermano", "titi", 0.25f)
    ).observable()


    var tabpane = tabpane {
        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
        setMinSize(200.0, 250.0)
    }

    var sb = squeezebox {
        fold("Edition", expanded = true) {
            tableview<Traduction> {
                //items = trads
                items = trads.sorted()
                //items = trads.sortWith(getProperty(Traduction::proba))
                //items = trads.sorted { t1, t2 -> return (if (t1.proba < t2.proba) t2 else t1)}
                //items = trads.sortBy { t -> t.proba }
                //items = trads.sortedWith(compareBy<Traduction>(Traduction::proba))
                column("FR", Traduction::frVal)
                column("EN", Traduction::enVal)
                column("ES", Traduction::esVal)
                column("CN", Traduction::cnVal)
            }
        }
    }

    override val root = borderpane {
        top = menubar {
            menu("File") {
                menuitem("Ouvrir","Shortcut+O") {
                    val exts = FileChooser.ExtensionFilter("Tableaux", listOf("*.csv", "*.CSV"))
                    chooseFile("Ouvrir Correspondance", arrayOf(exts), tornadofx.FileChooserMode.Multi,  null) {

                    }.forEach { f ->
                        println("choose "+f.absolutePath)
                        tabpane.tabpaneContent(f)
                        sb.tabpaneContent(f)
                    }
                }
                menuitem("Quit","Shortcut+Q") {
                    println("Quitting!")
                }
            }
        }
        center =
            vbox {
                add(tabpane)
                /*tab("Configuration", HBox()) {
                    tableview<Traduction> {
                        items = trads.sorted()
                        column("FR", Traduction::frVal)
                        column("EN", Traduction::enVal)
                        column("ES", Traduction::esVal)
                        column("CN", Traduction::cnVal)
                    }
                    button("Vérifier") {
                        useMaxWidth = true
                        action { println("JSON : " + trads[0].toJSON()) }
                    }
                }*/
                /*squeezebox {
                    fold("Edition", expanded = true) {
                        tableview<Traduction> {
                            //items = trads
                            items = trads.sorted()
                            //items = trads.sortWith(getProperty(Traduction::proba))
                            //items = trads.sorted { t1, t2 -> return (if (t1.proba < t2.proba) t2 else t1)}
                            //items = trads.sortBy { t -> t.proba }
                            //items = trads.sortedWith(compareBy<Traduction>(Traduction::proba))
                            column("FR", Traduction::frVal)
                            column("EN", Traduction::enVal)
                            column("ES", Traduction::esVal)
                            column("CN", Traduction::cnVal)
                        }
                    }
                }*/
                add(sb)
            }
        /*bottom = button("Quitter") {
            action { println("Quitter") }
        }*/
    }

    fun SqueezeBox.tabpaneContent(f: File) {
        if (f != null) {
            squeezebox {
                fold("Edition", expanded = true) {
                    tableview<Traduction> {
                        //trads = getDatas()
                        //items = trads.sorted()
                        var lines = f.readLines()
                        items = lines.map { l ->
                            if (l.isEmpty())
                                Traduction("","","","",0.5f)
                            else {
                                println(l)
                                val vals = l.split("\t")
                                //Traduction(l, "study", "estudiar", "shui", 0.5f)
                                Traduction(vals[0], vals[1], vals[2], "", 0.5f)
                            }
                        }.observable<Traduction>()
                        /*items = f.forEachLine { l ->
                            Traduction(l, "study", "estudiar", "shui", 0.5f)
                        }.observable<Traduction>()*/
                        /*items = listOf( Traduction("étudier", "study", "estudiar", "shui", 0.5f),
                                Traduction("amour", "love", "amor", "ai", 0.75f),
                                Traduction("frère", "brother", "hermano", "titi", 0.25f)
                        ).observable()*/
                        /*column("FR", Traduction::frVal)
                        column("EN", Traduction::enVal)
                        column("ES", Traduction::esVal)
                        column("CN", Traduction::cnVal)*/
                        var headers = lines[0].split("\t")
                        column(headers[0], Traduction::frVal)
                        column(headers[1], Traduction::enVal)
                        column(headers[2], Traduction::esVal)
                        column("CN", Traduction::cnVal)
                    }
                }
            }
        }
    }

    fun TabPane.tabpaneContent(f: File) {
        if (f == null)
            listOf("Espagnol", "Anglais", "Chinois").map { s ->
                tab(s, VBox()) {
                    form {
                        gridpane {
                            fieldset(labelPosition = Orientation.VERTICAL) {
                                hbox {
                                    vbox {
                                        field(s) {
                                            textfield()
                                        }
                                        button("Générer") {
                                            useMaxWidth = true
                                        }
                                    }
                                    //.gridpaneConstraints {columnRowIndex(0,0)}
                                    vbox {
                                        field("Français") {
                                            textfield()
                                        }
                                        button("Générer") {
                                            useMaxWidth = true
                                        }
                                    }
                                    //.gridpaneConstraints {columnRowIndex(1,0)}
                                }
                            }
                            label("Es = Fr") {
                                useMaxWidth = true
                                gridpaneConstraints {
                                    columnRowIndex(0, 1)
                                    marginTop = 10.0
                                    columnSpan = 2
                                }
                            }
                            label("C'est une réponse exacte") {
                                useMaxWidth = true
                                gridpaneConstraints {
                                    columnRowIndex(0, 2)
                                    marginTop = 10.0
                                    columnSpan = 2
                                }
                            }
                            button("Vérifier") {
                                useMaxWidth = true
                                gridpaneConstraints {
                                    columnRowIndex(0, 3)
                                    marginTop = 10.0
                                    columnSpan = 2
                                }
                                action { println("Wrote to database!") }
                            }
                        }
                    }
                }
            }
        else {

            tab(f.name, HBox()) {
                form {
                    gridpane {
                        fieldset(labelPosition = Orientation.VERTICAL) {
                            hbox {
                                vbox {
                                    field(f.name) {
                                        textfield()
                                    }
                                    button("Générer") {
                                        useMaxWidth = true
                                    }
                                }
                                //.gridpaneConstraints {columnRowIndex(0,0)}
                                vbox {
                                    field("Français") {
                                        textfield()
                                    }
                                    button("Générer") {
                                        useMaxWidth = true
                                    }
                                }
                                //.gridpaneConstraints {columnRowIndex(1,0)}
                            }
                        }
                        label("Es = Fr") {
                            useMaxWidth = true
                            gridpaneConstraints {
                                columnRowIndex(0, 1)
                                marginTop = 10.0
                                columnSpan = 2
                            }
                        }
                        label("C'est une réponse exacte") {
                            useMaxWidth = true
                            gridpaneConstraints {
                                columnRowIndex(0, 2)
                                marginTop = 10.0
                                columnSpan = 2
                            }
                        }
                        button("Vérifier") {
                            useMaxWidth = true
                            gridpaneConstraints {
                                columnRowIndex(0, 3)
                                marginTop = 10.0
                                columnSpan = 2
                            }
                            action { println("Wrote to database!") }
                        }
                    }
                }
            }
            squeezebox {
                fold("Edition", expanded = true) {
                    tableview<Traduction> {
                        //trads = getDatas()
                        //items = trads.sorted()
                        var lines = f.readLines()
                        items = lines.map { l ->
                            if (l.isEmpty())
                                Traduction("","","","",0.5f)
                            else {
                                println(l)
                                val vals = l.split("\t")
                                //Traduction(l, "study", "estudiar", "shui", 0.5f)
                                Traduction(vals[0], vals[1], vals[2], "", 0.5f)
                            }
                        }.observable<Traduction>()
                        /*items = f.forEachLine { l ->
                            Traduction(l, "study", "estudiar", "shui", 0.5f)
                        }.observable<Traduction>()*/
                        /*items = listOf( Traduction("étudier", "study", "estudiar", "shui", 0.5f),
                                Traduction("amour", "love", "amor", "ai", 0.75f),
                                Traduction("frère", "brother", "hermano", "titi", 0.25f)
                        ).observable()*/
                        /*column("FR", Traduction::frVal)
                        column("EN", Traduction::enVal)
                        column("ES", Traduction::esVal)
                        column("CN", Traduction::cnVal)*/
                        var headers = lines[0].split("\t")
                        column(headers[0], Traduction::frVal)
                        column(headers[1], Traduction::enVal)
                        column(headers[2], Traduction::esVal)
                        column("CN", Traduction::cnVal)
                    }
                }
            }
        }
    }

    fun getDatas() {
        listOf( Traduction("étudier", "study", "estudiar", "shui", 0.5f),
                Traduction("amour", "love", "amor", "ai", 0.75f),
                Traduction("frère", "brother", "hermano", "titi", 0.25f)
        ).observable()
    }

}

//class Traduction (frVal: String, val enVal: String, val esVal: String, val cnVal: String, proba:Float) {
class Traduction (frVal: String, enVal: String, esVal: String, cnVal: String, proba:Float) : JsonModel {
    var frVal by property(frVal)
//    fun frValProperty() = getProperty(Traduction::frVal)
    var enVal by property(enVal)
//    fun enValProperty() = getProperty(Traduction::enVal)
    var esVal by property(esVal)
//    fun esValProperty() = getProperty(Traduction::esVal)
    var cnVal by property(cnVal)
//    fun cnValProperty() = getProperty(Traduction::cnVal)
    var proba by property(proba)
    fun probaProperty() = getProperty(Traduction::proba)


    override fun updateModel(json: JsonObject) {
        with(json) {
            frVal = string("frVal")
            enVal = string("enVal")
            esVal = string("esVal")
            cnVal = string("cnVal")
        }
    }

    override fun toJSON(json: JsonBuilder) {
        with(json) {
            add("frVal", frVal)
            add("enVal", enVal)
            add("esVal", esVal)
            add("cnVal", cnVal)
        }
    }
}
