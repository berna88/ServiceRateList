package ServiceRateList.application;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.osgi.service.component.annotations.Component;

import com.consistent.rate.service.ServicesRest;

/**
 * @author liferay
 */
@ApplicationPath("/rates")
@Component(immediate=true,service=Application.class)

public class ServiceRateListApplication extends Application {
	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<Object>();
		singletons.add(new ServicesRest());
		return singletons;
	}
}