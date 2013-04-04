
package deductions.runtime.swing;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;

import javax.swing.JDialog;
import javax.swing.JTextArea;


//import org.apache.log4j.Logger;
//
//import eulergui.EulerGUI;
//import eulergui.gui.main.ProjectGUI;
//import eulergui.gui.main.ProjectGUIs;
//import eulergui.util.StackHelper;

/** */
public class TemporaryFrame
// extends JFrame
{
	public static final int ERROR = 0;
    public static final int WARNING = ERROR + 1;
    public static final int INFO = ERROR - 1;
    
    private JTextArea textArea;
//    private static final Logger _log = Logger.getLogger(TemporaryFrame.class);
	private JDialog frame;

	private int millis = 20000;
	private int status = INFO;

	public TemporaryFrame() {	
        textArea = new JTextArea();
	}
	
	/** defaults : 20 seconds, INFO color */
    public TemporaryFrame(final String localizedMessage) {
        this(localizedMessage, 20000);
    }

    public TemporaryFrame(String localizedMessage, final int millis, int status) {
    	this.millis = millis;
    	this.status = status;
    	displayMessage(localizedMessage);
//        _log.error("TemporaryFrame.TemporaryFrame(): " + localizedMessage
//				+ (status == WARNING ? " WARNING" : " ERROR"));
    }

	private void displayMessage(String localizedMessage) {
		if( localizedMessage.length() == 0 ) {
			return;
		}

		if (GraphicsEnvironment.isHeadless()) {
			System.err.println(localizedMessage);

		} else {
            if (localizedMessage != null && !"".equals(localizedMessage)) {
//				ProjectGUIs instance = ProjectGUIs.instance();
//				ProjectGUI owner = instance.getProjectGUI().size() > 0 ? instance.getProjectGUI()
//						.get(0) : null;
                textArea = new JTextArea(localizedMessage);
                textArea.setFont(textArea.getFont().deriveFont(28));
                setStatus(status);
                if( frame == null || ! frame.isVisible() ) {
                	frame = new JDialog( (JDialog)null,
//                			owner,
                			"", false);
                	frame.setTitle("NARS Message");
                	frame.getContentPane().add(textArea);				
                	frame.pack();
                	frame.setVisible(true);
                	Rectangle r = frame.getBounds();
                	r.x += 50; r.y += 50;
                	frame.setBounds(r);
                	textArea.setToolTipText( "Click to remove this popup." );
                	textArea.addMouseListener(new MouseListener(){
						@Override
						public void mouseClicked(MouseEvent e) {
							frame.dispose();
						}
						@Override
						public void mousePressed(MouseEvent e) {}
						@Override
						public void mouseReleased(MouseEvent e) {}
						@Override
						public void mouseEntered(MouseEvent e) {}
						@Override
						public void mouseExited(MouseEvent e) {}
					});
                }
                final Thread t = new Thread() {
                    @Override
                    public void run() {
                        try {
                            sleep(millis);
                        } catch (final InterruptedException e) {}
						frame.dispose();
                    }
                };
                t.start();
            }
        }
	}

    public TemporaryFrame(final String localizedMessage, final int millis) {
        this(localizedMessage, millis, INFO);
    }

    public void setLocalizedMessage(String displayMessage) {
    	if( ! displayMessage.equals( getLocalizedMessage() ) ) {
    		displayMessage(displayMessage);
//    		textArea.setText(displayMessage);
    	}
    }

    public void setStatus(int status) {
        if (status == ERROR) {
            textArea.setBackground(Color.RED);
        } else if (status == WARNING) {
            textArea.setBackground(Color.ORANGE);
        }
    }

    public String getLocalizedMessage() {
        return textArea.getText();
    }

    /**
     * show Exception in TemporaryFrame
     *
     * @param e1
     * @return
     */
    public static TemporaryFrame showException(Exception e) {
    	return showException("", e, "");
    }

    public static TemporaryFrame showException(String before, Exception e1, String after) {
        return showException(before, e1, after, ImageObserver.ERROR);
    }

    /**
     * show Exception in TemporaryFrame for 50 seconds
     *
     * @param before string to display before Exception
     * @param e1
     * @param after string to display after Exception
     * @return
     */
    public static TemporaryFrame showException(String before, Exception e1, String after, int status) {
		String localizedMessage = before + "\n"
				+ ( e1 == null ? "" : e1.getLocalizedMessage() )
				+ printStackTraceIfVerboseMode(e1);
		
		if ( e1 != null && e1.getCause() != null) {
			localizedMessage += "\nCause:\n";
			localizedMessage += e1.getCause()
					+ printStackTraceIfVerboseMode(e1.getCause());
		}
		localizedMessage += "\n" + after;
		return new TemporaryFrame(localizedMessage, 50000, status);
    }

	private static String printStackTraceIfVerboseMode(Throwable e1) {
		return e1.toString();
//				EulerGUI.instance().isVerboseMode()
//				? "\n" + StackHelper.customPrintStackTrace(e1)
//				: "" ;
	}

	/**
	 * @param b
	 */
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
