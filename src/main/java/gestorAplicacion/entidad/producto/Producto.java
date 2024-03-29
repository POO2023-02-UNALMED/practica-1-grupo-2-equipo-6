package gestorAplicacion.entidad.producto;

import baseDatos.impl.CompradorRepositorio;
import baseDatos.impl.ProductoRepositorio;

import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Membresia;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.ProductoTransaccion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.Orden;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Publicacion;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Producto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nombre;
    private Categoria categoria;
    private List<OpinionProducto> opinion;
    private List<Publicacion> publicaciones;
    private List<Comprador> compradores;
    private List<Comprador> resenadores;

	public Producto(Long id, String nombre, Categoria categoria) {
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.opinion = new ArrayList<>();
		this.compradores = new ArrayList<>();
		this.publicaciones = new ArrayList<>();
		this.resenadores=new ArrayList<>();
	}
	 
	

	public void addOpinionProducto(OpinionProducto resena) {
		if (Objects.isNull(resena))
			return; // Creacion del metodo addOpinion como en la clase vendedor
		opinion.add(resena);

	}

	public boolean existeResena(Comprador comprador) {
				
		return resenadores.contains(comprador);          // Creacion del metodo ExisteResena que comprueba si ya hay una rese�a

	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public List<OpinionProducto> getOpiniones() {
		return opinion;
	}

	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void agregarPublicacion(Publicacion publicacion) {
		publicaciones.add(publicacion);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setComprodores(List<Comprador> compradores) {
		this.compradores = compradores;
	}

	public List<Comprador> getCompradores() {
		return compradores;
	}

	public List<Comprador> getResenadores() {
		return resenadores;
	}

	public void setOpinion(List<OpinionProducto> opinion) {
		this.opinion = opinion;
	}

	public void setCompradores(List<Comprador> compradores) {
		this.compradores = compradores;
	}

	public void setResenadores(List<Comprador> resenadores) {
		this.resenadores = resenadores;
	}

	public boolean isPerecedero() {
		return categoria.isPerecedero();
	}

	public void agregarComprador(Comprador comprador) {
		compradores.add(comprador);
	}

	public void agregarResenador(Comprador resenador) {
		resenadores.add(resenador);
	}



	public static List<Producto> getProductos() {
		return ProductoRepositorio.getProductos();
	}

	
	public static Producto productoMasVendido() {
		Map<Producto, Integer> producto = new HashMap<>();
		for (Comprador comprador : CompradorRepositorio.obtener()) {
			for (Orden orden : comprador.getOrdenes()) { // cambiarlo por transaccion
				for (ProductoTransaccion productoTransaccion : orden.getProductosTransaccion()) {
					if (producto.containsKey(productoTransaccion.getPublicacion().getProducto()))
						producto.merge(productoTransaccion.getPublicacion().getProducto(), 1, Integer::sum);
					else
						producto.put(productoTransaccion.getPublicacion().getProducto(), 1);

				}

			}

		}

		Producto ProductoMasVendido = null;
		int valor = 0;
		for (Map.Entry<Producto, Integer> entry : producto.entrySet()) {
			if (entry.getValue() > valor) {
				valor = entry.getValue();
				ProductoMasVendido = entry.getKey();
			}

		}
		return ProductoMasVendido;
	}




		public static String productoMasCaro() {
			float productoMasCaro = 0;
			String productoCaro=null;
			for (Comprador comprador : CompradorRepositorio.obtener()) {
				for (Orden orden : comprador.getOrdenes()) {
					for (ProductoTransaccion productoTransaccion : orden.getProductosTransaccion()) {
						if (productoMasCaro < (productoTransaccion.getPublicacion().getPrecio()))
							
						{
							productoMasCaro = productoTransaccion.getPublicacion().getPrecio();
							productoCaro=productoTransaccion.getPublicacion().getProducto().getNombre();

						}
					}

				}
			}
			return (productoMasCaro+" que vale "+productoCaro);
		}
	


   
		public static float ventasTotales() {
			float valorVentas = 0;
			for (Comprador comprador : CompradorRepositorio.obtener()) {
				for (Orden orden : comprador.getOrdenes()) {
					for (ProductoTransaccion productoTransaccion : orden.getProductosTransaccion()) {
						valorVentas += (productoTransaccion.getPublicacion().getPrecio()*productoTransaccion.getCantidad());
					}

				}
			}
			return valorVentas;
		}
		
		public static int productosTotalesVendidos() {
			int valorVendidos = 0;
			for (Comprador comprador : CompradorRepositorio.obtener()) {
				for (Orden orden : comprador.getOrdenes()) {
					for (ProductoTransaccion productoTransaccion : orden.getProductosTransaccion()) {
						valorVendidos ++;
					}

				}
			}
			return valorVendidos;
		}



		public static String productoMasBarato() {
			float productoMasBarato = Float.MAX_VALUE;
			String productoBarato=null;
			for (Comprador comprador : CompradorRepositorio.obtener()) {
				for (Orden orden : comprador.getOrdenes()) {
					for (ProductoTransaccion productoTransaccion : orden.getProductosTransaccion()) {
						if (productoMasBarato > (productoTransaccion.getPublicacion().getPrecio()))
							
						{
							productoMasBarato = productoTransaccion.getPublicacion().getPrecio();
							productoBarato=productoTransaccion.getPublicacion().getProducto().getNombre();

						}
					}

				}
			}
			return (productoBarato+" que vale "+ productoMasBarato);
		}

		


		public static Membresia MembresiaMasComprada() {
	        int Ninguna = 0;
	        int Oro = 0;
	        int Plata = 0;
	        int Bronce = 0;
	        int Basica = 0;

	        for (Comprador comprador : CompradorRepositorio.obtener()) {
	            Membresia comparar = comprador.getMembresia();
	            switch (comparar) {
	                case NINGUNA:
	                    Ninguna++;
	                    break;
	                case BASICA:
	                    Basica++;
	                    break;
	                case BRONCE:
	                    Bronce++;
	                    break;
	                case PLATA:
	                    Plata++;
	                    break;
	                case ORO:
	                    Oro++;
	                    break;
	            }
	        }

	        // Encuentra la membresía más común
	        Membresia membresiaMasComun = Membresia.NINGUNA;
	        int maxCompras = Ninguna;

	        if (Basica > maxCompras) {
	            membresiaMasComun = Membresia.BASICA;
	            maxCompras = Basica;
	        }
	        if (Bronce > maxCompras) {
	            membresiaMasComun = Membresia.BRONCE;
	            maxCompras = Bronce;
	        }
	        if (Plata > maxCompras) {
	            membresiaMasComun = Membresia.PLATA;
	            maxCompras = Plata;
	        }
	        if (Oro > maxCompras) {
	            membresiaMasComun = Membresia.ORO;
	        }

	        return membresiaMasComun;
	    }
		public static int cantidadProductosVendidos() {
		    Set<Producto> productosDiferentes = new HashSet<>();

		    for (Comprador comprador : CompradorRepositorio.obtener()) {
		        for (Orden orden : comprador.getOrdenes()) {
		            for (ProductoTransaccion productoTransaccion : orden.getProductosTransaccion()) {
		                Producto producto = productoTransaccion.getPublicacion().getProducto();
		                productosDiferentes.add(producto);
		            }
		        }
		    }

		    return productosDiferentes.size();
		}	
}


