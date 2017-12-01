package proj;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import jayeson.lib.datastructure.PivotType;
import jayeson.lib.datastructure.Record;
import jayeson.lib.datastructure.SoccerEvent;
import jayeson.lib.datastructure.SoccerEventLiveState;
import jayeson.lib.recordfetcher.DeltaEventHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Galimov
 */
public class EventHandler implements DeltaEventHandler {
    private static final Logger logger = LogManager.getLogger(EventHandler.class);
    BetsProccessor bp = new BetsProccessor();

    public EventHandler() {
        UserCredit userCredit = bp.getUserCredit();
        System.out.println(userCredit.toString());
    }

    @Override
    public void onNewEvents(List<SoccerEvent> newEvents) {
        logger.debug("onNewEvents");
        System.out.println("Created Events:");
        for(SoccerEvent se: newEvents) {
            System.out.println(String.format("Id %s \t-\t Host %s \t-\t Guest %s \t-\t League %s", se.getEventId(), se.getHost(), se.getGuest(), se.getLeague()));
            for (Record rec : se.getRecords()) {
                System.out.println(String.format(" OddId: %d", rec.getOddId()));
            }            
        }


    }

    @Override
    public void onChangedEvents(List<SoccerEvent> changedEvents) {
        logger.debug("onChangedEvents");
        System.out.println("Updated Events:");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:SS z");
        for(SoccerEvent se: changedEvents) {
            System.out.println(String.format("Id %s \t-\t Host %s \t-\t Guest %s \t-\t League %s", se.getEventId(), se.getHost(), se.getGuest(), se.getLeague()));

            SoccerEventLiveState state = se.getLiveState();
            Date d = new Date(state.getStartTime()*1000);
            System.out.println(String.format("LiveState -- Starttime %d (%s) Source %s - Duration %d - Score %d-%d,", state.getStartTime(),
                dateFormat.format(d), state.getSource(), state.getDuration(), state.getHostPoint(), state.getGuestPoint() ) + " OddType: " + state.getMarketType());
            for (Record rec : se.getRecords()) {
                System.out.println(String.format(" OddId: %d", rec.getOddId()));
            }
        }

    }

    @Override
    public void onDeletedEvents(List<SoccerEvent> deletedEvents) 
    {
//		System.out.println("Deleted Events:");
//		for(SoccerEvent se: deletedEvents)
//		{
//			System.out.println(String.format("Id %s \t-\t Host %s \t-\t Guest %s \t-\t League %s", se.getEventId(), se.getHost(), se.getGuest(), se.getLeague()));
//		}


    }

    @Override
    public void onChangedOdds(List<SoccerEvent> changedOdds)
    {
//                logger.debug("onChangedOdds");
//		System.out.println("Updated Odds");
//		for(SoccerEvent se: changedOdds)
//		{
//			System.out.println(String.format("Id %s \t-\t Host %s \t-\t Guest %s \t-\t League %s", se.getEventId(), se.getHost(), se.getGuest(), se.getLeague()));
//			Collection<Record> rs = se.getRecords();
//			for (Record r : rs) {
// 		         System.out.println("("+r.getOddId() + " " +r.getSource()+" "+r.getOddType()+" "+r.getPivotType()+" "+r.getPivotValue()+" "+r.getPivotString()+" "+
//			                               " " + r.getRateOverUid() + " " +  r.getRateOver()+" "+
// 		        		                   r.getRateUnderUid() + " " + r.getRateUnder()+" "+
//			                               r.getRateEqualUid() + " " + r.getRateEqual()+")");
//			}
//		}
//		
    }

    @Override
    public void onDeletedOdds(List<SoccerEvent> deletedOdds)
    {
//		System.out.println("Delteted Odds");
//		for(SoccerEvent se: deletedOdds)
//		{
//			System.out.println(String.format("Id %s \t-\t Host %s \t-\t Guest %s \t-\t League %s", se.getEventId(), se.getHost(), se.getGuest(), se.getLeague()));
//			Collection<Record> rs = se.getRecords();
//			for (Record r : rs) {
// 		         System.out.println("("+r.getOddId() + " " +r.getSource()+" "+r.getOddType()+" "+r.getPivotType()+" "+r.getPivotValue()+" "+r.getPivotString()+" "+
//			                               " " + r.getRateOverUid() + " " +  r.getRateOver()+" "+
// 		        		                   r.getRateUnderUid() + " " + r.getRateUnder()+" "+
//			                               r.getRateEqualUid() + " " + r.getRateEqual()+")");
//			}
//		}
    }

    @Override
    public void onRefreshedOdds(List<String> refreshedSources) {
            System.out.println("Refreshed Odds");
            for(String source:refreshedSources)
                    System.out.println(String.format(" Source %s Refreshed",source));


    }

    @Override
    public void onNewOdds(List<SoccerEvent> newOdds) {
        System.out.println("New Odds");
        for(SoccerEvent se: newOdds) {
            System.out.println(String.format("Id %s \t-\t Host %s \t-\t Guest %s \t-\t League %s", se.getEventId(), se.getHost(), se.getGuest(), se.getLeague()));
            Collection<Record> rs = se.getRecords();
            for (Record r : rs) {
             System.out.println("("+r.getOddId() + " " +r.getSource()+" "+r.getOddType()+" "+r.getPivotType()+" "+r.getPivotValue()+" "+r.getPivotString()+" "+
                                           " " + r.getRateOverUid() + " " +  r.getRateOver()+" "+
                                               r.getRateUnderUid() + " " + r.getRateUnder()+" "+
                                           r.getRateEqualUid() + " " + r.getRateEqual()+")");
             
             String tt;
             if (r.getPivotType() == PivotType.HDP) tt = "home";
             else tt = "over";
             BetTicket betTicket = bp.getBetTicket(r.getSource(), tt, r.getOddType().toString(), r.getEventId(), ""+r.getOddId());
             System.out.println(betTicket.toString());
             
            }
        }

    }

    @Override
    public void onSecondaryLiveStateChange(List<SoccerEvent> changedEvents) {
//		System.out.println("Secondary live state changes");
//		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:SS z");
//		for(SoccerEvent se: changedEvents)
//		{
//			System.out.println(String.format("Id %s \t-\t Host %s \t-\t Guest %s \t-\t League %s", se.getEventId(), se.getHost(), se.getGuest(), se.getLeague()));
//			
//			Collection<SoccerEventLiveState> states = se.getAllLiveState();
//			for(SoccerEventLiveState state:states){
//				Date d = new Date(state.getStartTime()*1000);
//				System.out.println(String.format("LiveState -- Starttime %d (%s) Source %s - Duration %d - Score %d-%d", state.getStartTime(),
//												dateFormat.format(d), state.getSource(), state.getDuration(), state.getHostPoint(), state.getGuestPoint()));
//				
//			}
//		}
//
    }
    
}
