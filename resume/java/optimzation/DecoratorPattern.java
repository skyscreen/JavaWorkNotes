package resume.java.optimzation;

public class DecoratorPattern {

	public static void main(String[] args) {
		DecoratorPattern d = new DecoratorPattern();
		IPacketCreator pc = d.new PacketHTTPHeaderCreator(d.new PacketHTMLHeaderCreator(d.new PacketBodyCreator()));
		System.out.println(pc.handleContent());
	}

	public interface IPacketCreator {
		public String handleContent();
	}

	public class PacketBodyCreator implements IPacketCreator {
		@Override
		public String handleContent() {
			return "Content of Packet";
		}
	}

	public abstract class PacketDecorator implements IPacketCreator {
		IPacketCreator componet;

		public PacketDecorator(IPacketCreator c) {
			componet = c;
		}
	}

	public class PacketHTMLHeaderCreator extends PacketDecorator {

		public PacketHTMLHeaderCreator(IPacketCreator c) {
			super(c);
		}

		@Override
		public String handleContent() {
			StringBuffer sb = new StringBuffer();
			sb.append("<html>");
			sb.append("<body>");
			sb.append(componet.handleContent());
			sb.append("</body>");
			sb.append("</html>\n");
			return sb.toString();
		}
	}

	public class PacketHTTPHeaderCreator extends PacketDecorator {
		public PacketHTTPHeaderCreator(IPacketCreator c) {
			super(c);
		}

		@Override
		public String handleContent() {
			StringBuffer sb = new StringBuffer();
			sb.append("Cache-Control:no-cache\n");
			sb.append("Date:Mon,31Dec201204:25:57GMT\n");
			sb.append(componet.handleContent());
			return sb.toString();
		}
	}

}
