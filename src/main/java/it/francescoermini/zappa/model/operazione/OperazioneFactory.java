package it.francescoermini.zappa.model.operazione;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import it.francescoermini.zappa.model.registro.RegistroCantina;
import it.francescoermini.zappa.model.type.LuogoType;

public class OperazioneFactory {
	
	public static Object createOperazione(OperazioneType operazioneType){
		if(operazioneType == null ){
			
			return null;
		}
		Class<?> c = operazioneType.getClasse();	
		Class [] paramTypes = { String.class, OperazioneType.class };
        Object [] paramValues = {  UUID.randomUUID().toString(), operazioneType };

			Object obj = null;
			try {
				Constructor<?> cons  = c.getDeclaredConstructor(paramTypes);
				obj = cons.newInstance( paramValues);
				c.cast(obj);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				System.out.print("FALLITO A ISTANZIARE OPERAZIONE SPECIFICA");
				e.printStackTrace();
			}
			return obj;		
	}
	
	
	
	
	
//	
//	
//	public static Operazione operazione(OperazioneType operationType){
//
//			
//			if ( operationType ==OperazioneType.VENDEMMIA){
//				return (OperazioneVendemmia)new OperazioneVendemmia(UUID.randomUUID().toString(), operationType.getLuogoType());
//			}
//			else if(operationType ==OperazioneType.TRATTAMENTO){
//				 return (OperazioneTrattamento)new OperazioneTrattamento(UUID.randomUUID().toString(), operationType.getLuogoType());
//			}
//			else if(operationType ==OperazioneType.RIMONTAGGIO){
//				return (OperazioneRimontaggio)new OperazioneRimontaggio(UUID.randomUUID().toString(),  operationType.getLuogoType());
//			}
//			else if(operationType ==OperazioneType.MISURAZIONE){
//				return (OperazioneMisurazione) new OperazioneMisurazione(UUID.randomUUID().toString(), operationType.getLuogoType());
//			}
//			else if(operationType ==OperazioneType.CONFERITURA){
//				return (OperazioneConferitura) new OperazioneConferitura(UUID.randomUUID().toString(), operationType.getLuogoType());
//			}
//			else if(operationType ==OperazioneType.PIGIATURADIRASPATURA){
//				return (OperazionePigiaturaDiraspatura) new OperazionePigiaturaDiraspatura(UUID.randomUUID().toString(), operationType.getLuogoType());
//			}
//			else if(operationType ==OperazioneType.FERMENTAZIONE){
//				return (OperazioneFermentazione) new OperazioneFermentazione(UUID.randomUUID().toString(), operationType.getLuogoType());
//			}
//			else if(operationType ==OperazioneType.CORREZIONITAGLI){
//				return (OperazioneCorrezioniTagli) new OperazioneCorrezioniTagli(UUID.randomUUID().toString(), operationType.getLuogoType());
//			}
//			else if(operationType ==OperazioneType.SVINATURA){
//				return (OperazioneSvinatura) new OperazioneSvinatura(UUID.randomUUID().toString(), operationType.getLuogoType());
//			}
//			else{
//				return null;
//			}
//			
//	}
	
	
	public static Operazione casting(Operazione operazione){
		if (operazione instanceof OperazioneVendemmia ){
			return (OperazioneVendemmia) operazione;
		}
		if (operazione instanceof OperazioneTrattamento ){
			return (OperazioneTrattamento) operazione;
		}
		if (operazione instanceof OperazioneRimontaggio ){
			return (OperazioneRimontaggio) operazione;
		}
		if (operazione instanceof OperazioneMisurazione ){
			return (OperazioneMisurazione) operazione;
		}
		if (operazione instanceof OperazioneConferitura ){
			return (OperazioneConferitura) operazione;
		}
		if (operazione instanceof OperazionePigiaturaDiraspatura ){
			return (OperazionePigiaturaDiraspatura) operazione;
		}
		if (operazione instanceof OperazioneFermentazione ){
			return (OperazioneFermentazione) operazione;
		}
		if (operazione instanceof OperazioneSvinatura ){
			return (OperazioneSvinatura) operazione;
		}
		if (operazione instanceof OperazioneCorrezioniTagli ){
			return (OperazioneCorrezioniTagli) operazione;
		}
		return (Operazione)operazione;
	}


	public static RegistroCantina registroCantina() {
		
		return new RegistroCantina();
	}
	
}
	
//	public static <T> T getParentComponent(Operazione component, Class<T> c)
//	{
//	    Operazione parent = component.getParent();
//	    while (parent != null)
//	    {
//	        if (c.isInstance(parent))
//	        {
//	            return c.cast(parent);
//	        }
//	        parent = parent.getParent();
//	    }
//	    // parent == null, so nothing found
//	    return null;
//	}

	
	
	
		
//	@SuppressWarnings("unchecked")
//	public static void castingList(List<Operazione> operazioni){
//		if (operazioni.get(0) instanceof OperazioneVendemmia ){
//			return castCollection(operazioni, OperazioneVendemmia.class);
//		}
//		if (operazione instanceof OperazioneTrattamento ){
//			return (OperazioneTrattamento) operazione;
//		}
//		if (operazione instanceof OperazioneRimontaggio ){
//			return (OperazioneRimontaggio) operazione;
//		}
//		if (operazione instanceof OperazioneMisurazione ){
//			return (OperazioneMisurazione) operazione;
//		}
//		return null;
//	}
//	
//	public static void isOperazione(Operazione operazione){
//		if (operazione instanceof OperazioneVendemmia ){
//			operazione.loo
//		}
//	}
//	
//	public static OperazioneVendemmia op(Operazione operazione){
//		
//	}
//	public static <T>List<T> castCollection(List srcList, Class<T> clas){
//	    List<T> list =new ArrayList<T>();
//	    for (Object obj : srcList) {
//	    if(obj!=null && clas.isAssignableFrom(obj.getClass()))
//	        list.add(clas.cast(obj));
//	    }
//	    return list;
//	}
//}
