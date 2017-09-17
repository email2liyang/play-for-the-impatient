import com.google.inject.{AbstractModule, Singleton}
import model.{DefaultSummarizer, Summarizer}

class Module extends AbstractModule{

  override def configure(): Unit = {
    bind(classOf[Summarizer]).to(classOf[DefaultSummarizer]).in(classOf[Singleton])
  }
}
