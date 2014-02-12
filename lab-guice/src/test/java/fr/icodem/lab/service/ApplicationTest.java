package fr.icodem.lab.service;

import com.google.inject.*;
import com.google.inject.name.Names;
import fr.icodem.lab.model.Order;
import fr.icodem.lab.model.Product;
import fr.icodem.lab.model.Stock;
import fr.icodem.lab.model.StockItem;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

import static junitparams.JUnitParamsRunner.$;

@RunWith(JUnitParamsRunner.class)
public class ApplicationTest {

    @Test @Parameters
    public void itShouldRemoveProductFromStock(Module module, short expectedProductCount) throws Exception {
        // arrange
        Product p = new Product(1, "Astérix en Corse");

        Order order = new Order();
        order.addLine(p, (short)1);

        Injector injector = Guice.createInjector(module);
        Application app = injector.getInstance(Application.class);

        // act
        app.saveOrder(order);
        short productCount = app.getProductCountInStock(1);

        // assert
        Assert.assertEquals("Wrong product count in stock", expectedProductCount, productCount);
    }

    private Object[] parametersForItShouldRemoveProductFromStock() {

        // ===============================================================================
        // Various kinds of injection
        // ===============================================================================

        // Field injection
        class AppModule1 extends AbstractModule {
            @Override
            protected void configure() {
                bind(OrderService.class).to(OrderServiceImpl1.class);
                bind(StockService.class).to(StockServiceImpl1.class);
                bind(Application.class).to(ApplicationImpl1.class);
            }
        }

        // Injection through constructor in Application object
        class AppModule2 extends AbstractModule {
            @Override
            protected void configure() {
                bind(OrderService.class).to(OrderServiceImpl2.class);
                bind(StockService.class).to(StockServiceImpl2.class);
                bind(Application.class).to(ApplicationImpl2.class);
            }
        }

        // Injection through setters in Application object
        class AppModule3 extends AbstractModule {
            @Override
            protected void configure() {
                bind(OrderService.class).to(OrderServiceImpl3.class);
                bind(StockService.class).to(StockServiceImpl3.class);
                bind(Application.class).to(ApplicationImpl3.class);
            }
        }


        // ===============================================================================
        // Scopes
        // ===============================================================================

        // Singleton is made with @Singleton
        class AppModule21 extends AbstractModule {
            @Override
            protected void configure() {
                bind(OrderService.class).to(OrderServiceImpl21.class);
                bind(StockService.class).to(StockServiceImpl21.class);
                bind(Application.class).to(ApplicationImpl21.class);
            }
        }

        // Singleton is made with method in(Singleton.class)
        class AppModule22 extends AbstractModule {
            @Override
            protected void configure() {
                bind(OrderService.class).to(OrderServiceImpl22.class);
                bind(StockService.class).to(StockServiceImpl22.class).in(Singleton.class);
                bind(Application.class).to(ApplicationImpl22.class);
            }
        }


        // ===============================================================================
        // Bindings
        // ===============================================================================

        // Linked bindings => see AppModule1

        // Binding Annotations => custom annotation
        class AppModule41 extends AbstractModule {
            @Override
            protected void configure() {
                bind(OrderService.class).annotatedWith(Implementation41.class).to(OrderServiceImpl41.class);
                bind(StockService.class).to(StockServiceImpl41.class);
                bind(Application.class).to(ApplicationImpl41.class);
            }
        }

        // Binding Annotations => @Named
        class AppModule42 extends AbstractModule {
            @Override
            protected void configure() {
                bind(OrderService.class).annotatedWith(Names.named("Implementation22")).to(OrderServiceImpl42.class);
                bind(StockService.class).to(StockServiceImpl42.class);
                bind(Application.class).to(ApplicationImpl42.class);
            }
        }

        // Instance bindings
        class AppModule43 extends AbstractModule {
            @Override
            protected void configure() {
                bind(OrderService.class).toInstance(new OrderServiceImpl43());
                bind(StockService.class).to(StockServiceImpl43.class);
                bind(Application.class).to(ApplicationImpl43.class);
                bind(Short.class).annotatedWith(Names.named("Alert Threshold")).toInstance((short)3);
                bind(Short.class).annotatedWith(Names.named("InitialQuantity")).toInstance((short)10);
                bind(String.class).annotatedWith(Names.named("Stock 23")).toInstance("The Stock 23");
            }
        }

        // Untargetted bindings
        class AppModule44 extends AbstractModule {
            @Override
            protected void configure() {
                bind(OrderServiceImpl44.class);// untargetted
                bind(StockServiceImpl44.class).in(Singleton.class);// untargetted
                bind(Application.class).to(ApplicationImpl44.class);// targetted
            }
        }

        // Constructor bindings
        class AppModule45 extends AbstractModule {
            @Override
            protected void configure() {
                bind(OrderService.class).to(OrderServiceImpl45.class);
                bind(StockService.class).to(StockServiceImpl45.class);
                try {
                    bind(Application.class).toConstructor(
                            ApplicationImpl45.class.getConstructor(OrderService.class, StockService.class));
                } catch (NoSuchMethodException e) {
                    addError(e);
                }
            }
        }

        // Built-in bindings
        // TODO add built-in binding

        // Just-In-Time bindings
        // TODO add JIT binding

        // @Provides methods
        class AppModule46 extends AbstractModule {
            @Override
            protected void configure() {
                bind(OrderService.class).to(OrderServiceImpl46.class);
                bind(StockService.class).to(StockServiceImpl46.class);
                bind(Application.class).to(ApplicationImpl46.class);
            }

            @Provides
            Stock provideStock() {
                Map<Integer, StockItem> map = new HashMap<>();

                Product p = new Product(1 , "Astérix en Corse");
                StockItem item = new StockItem(1, p, (short)6);
                map.put(p.getId(), item);
                Stock stock = new Stock(map);

                return stock;
            }
        }

        // Provider binding
        class AppModule47 extends AbstractModule {
            @Override
            protected void configure() {
                bind(OrderService.class).to(OrderServiceImpl47.class);
                bind(StockService.class).to(StockServiceImpl47.class);
                bind(Application.class).to(ApplicationImpl47.class);
                bind(Stock.class).toProvider(StockProvider47.class);
            }
        }

        // Provider binding with injection in provider
        class AppModule48 extends AbstractModule {
            @Override
            protected void configure() {
                bind(OrderService.class).to(OrderServiceImpl48.class);
                bind(StockService.class).to(StockServiceImpl48.class);
                bind(Application.class).to(ApplicationImpl48.class);
                bind(Stock.class).toProvider(StockProvider48.class);
                bind(Short.class).annotatedWith(Names.named("Initial Quantity")).toInstance((short)15);
            }
        }




        return $(// object = module, product count in stock

            // ===============================================================================
            // Various kinds of injection
            // ===============================================================================

            // In the following examples, StockService is not a singleton
            // => one instance injected in Application and another in OrderService,
            // so stock modifications are not reflected in Application object
            // Thus, product count is not decremented in StockService referenced in Application object
            // See scopes examples where the problem is solved with singleton

            // Field injection
            $(new AppModule1(), (short)5),

            // Constructor injection in Application object
            $(new AppModule2(), (short)5),

            // Setter injection in Application object
            $(new AppModule3(), (short)5),


            // ===============================================================================
            // Scopes
            // ===============================================================================

            // In the following examples, StockService is made a singleton
            // => stock modifications are now reflected in Application object
            // because the same instance is injected in Application and OrderService

            // Singleton is made with @Singleton
            $(new AppModule21(), (short)4),

            // Singleton is made with method in(Singleton.class)
            $(new AppModule22(), (short)4),


            // ===============================================================================
            // Bindings
            // ===============================================================================

            // Binding Annotations => custom annotation
            $(new AppModule41(), (short)4),

            // Binding Annotations => @Named
            $(new AppModule42(), (short)4),

            // Binding Annotations => @Named
            $(new AppModule43(), (short)9),

            // Untargetted binding
            $(new AppModule44(), (short)4),

            // Constructor binding
            $(new AppModule45(), (short)4),

            // @Provides methods
            $(new AppModule46(), (short)5),

            // Provider binding
            $(new AppModule47(), (short)6),

            // Provider binding
            $(new AppModule48(), (short)14)

        );

    }
}
