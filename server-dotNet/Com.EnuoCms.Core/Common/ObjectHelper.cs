using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Reflection;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
namespace Com.EnuoCms.Core.Common
{
	public static class ObjectHelper
	{
		public static void CopyProperties(object src, object des)
		{
			var sourceType = src.GetType();
			var targetType = des.GetType();

			Dictionary<string, object> values = new Dictionary<string, object>();
			var properties = GetMatchingProperties(sourceType, targetType);
			//typeof(T).GetProperties();
			foreach (var prop in properties)
			{
				//var prop = propMap[i];
				var sourceValue = prop.SourceProperty.GetValue(src, null);
				prop.TargetProperty.SetValue(des, sourceValue, null);
			}
		}

		public static void getPropertiesFrom(this object target, object source)
		{
			var sourceType = source.GetType();
			var targetType = target.GetType();
			var propMap = GetMatchingProperties(sourceType, targetType);

			for (var i = 0; i < propMap.Count; i++)
			{
				var prop = propMap[i];
				var sourceValue = prop.SourceProperty.GetValue(source, null);
				prop.TargetProperty.SetValue(target, sourceValue, null);
			}
		}

		public class PropertyMap
		{
			public PropertyInfo SourceProperty { get; set; }
			public PropertyInfo TargetProperty { get; set; }
		}

		public static IList<PropertyMap> GetMatchingProperties(Type sourceType, Type targetType)
		{
			var sourceProperties = sourceType.GetProperties();
			var targetProperties = targetType.GetProperties();

			var properties = (from s in sourceProperties
							  from t in targetProperties
							  where s.Name == t.Name &&
									s.CanRead &&
									t.CanWrite &&
									s.PropertyType.IsPublic &&
									t.PropertyType.IsPublic &&
									  s.PropertyType == t.PropertyType &&
									(
									  (s.PropertyType.IsValueType &&
									   t.PropertyType.IsValueType
									  ) ||
									  (s.PropertyType == typeof(string) &&
									   t.PropertyType == typeof(string)
									  )
									)

							  select new PropertyMap
							  {
								  SourceProperty = s,
								  TargetProperty = t
							  }).ToList();

			return properties;
		}

	}
}
