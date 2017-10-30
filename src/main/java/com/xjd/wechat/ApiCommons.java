package com.xjd.wechat;

/**
 * @author elvis.xu
 * @since 2017-10-30 15:41
 */
public interface ApiCommons {
	public static enum WLang {
		ZH_CN("zh_CN"), ZH_TW("zh_TW"), EN("en");

		String code;
		WLang(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}
	}

	public static enum Sex {
		UNKNOWN(0), MALE(1), FEMALE(2);

		int code;
		Sex(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}

		public static Sex ofCode(Integer code) {
			if (code == null) return null;
			for (Sex sex : Sex.values()) {
				if (sex.code == code) {
					return sex;
				}
			}
			return null;
		}
	}
}
