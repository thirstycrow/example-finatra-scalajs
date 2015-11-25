package myapp.frontend

import scala.scalajs.js.annotation.JSExport
import org.scalajs.dom
import scalatags.Text.all._
import rx.core.Var

@JSExport
object MyApp {

  implicit val bundle = scalatags.JsDom

  @JSExport
  def main() {
    dom.document.body.appendChild(RootLayout.render)
  }
}

object RootLayout {

  implicit val bundle = scalatags.JsDom

  import bundle.all._

  def render = {
    div(id := "root")(
      navbar.render,
      content
    ).render
  }
  
  val message = Var("item1")

  val navbar = new Menu("item1", "item2", "item3")

  val content = div(id := "content")(navbar.activeItem().label)

  implicit class MenuItem(val label: String)  
  
  class Menu(items: MenuItem*) {
    
    val activeItem = Var(items.head)
    
    def render = {
      div(id := "nav")(
        ul(items.map(renderItem))    
      )
    }
    
    def renderItem(item: MenuItem) = {
      ol(a(href := "#", onclick := { () => activeItem() = item })(item.label))
    }
  }
}
