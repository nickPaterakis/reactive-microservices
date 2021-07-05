/**
 * minLength Val
 * @param  value
 * @param  minLength
 * @return
 */
const minLengthValidator = (value, minLength) => value.length >= minLength;

/**
 * maxLength Val
 * @param  value
 * @param  maxLength
 * @return
 */
const maxLengthValidator = (value, maxLength) => value.length <= maxLength;

/**
 * Age Validator
 * @param  value
 * @return
 */
const ageValidator = (value) => value <= 140;

/**
 * Weight Validator
 * @param  value
 * @return
 */
const weightValidator = (value) => value <= 600;

/**
 * Height Validator
 * @param  value
 * @return
 */
const heightValidator = (value) => value <= 3;

/**
 * Positive Validator
 * @param  value
 * @return
 */
const positiveValidator = (value) => value > 0;

/**
 * Check to confirm that feild include only numbers
 *
 * @param  value
 * @return
 */
const onlyNumbersValidator = (value) => {
  const re = /^[0-9]*$/;
  return re.test(String(value));
};

/**
 * Check to confirm that feild include only numbers or decimal numbers
 *
 * @param  value
 * @return
 */
const onlyNumbersAndDecimalNumbersValidator = (value) => {
  const re = /^(\d*\.)?\d+$/;
  return re.test(String(value));
};

/**
 * Check to confirm that feild is required
 *
 * @param  value
 * @return
 */
const requiredValidator = (value) => value.trim() !== '';

/**
 * Email validation
 *
 * @param value
 * @return
 */
const emailValidator = (value) => {
  const re = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
  return re.test(String(value).toLowerCase());
};

const validate = (value, rules) => {
  for (const rule in rules) {
    if (Object.prototype.hasOwnProperty.call(rules, rule)) {
      switch (rule) {
        case 'minLength': {
          if (minLengthValidator(value, rules[rule])) break;
          else return { valid: false, errorMessage: `You need to type ${rules[rule]} characters` };
        }
        case 'maxLength': {
          if (maxLengthValidator(value, rules[rule])) break;
          else return { valid: false, errorMessage: `You should type until ${rules[rule]} characters` };
        }
        case 'isRequired': {
          if (requiredValidator(value)) break;
          else return { valid: false, errorMessage: 'You should fill this field' };
        }
        case 'isEmail': {
          if (emailValidator(value)) break;
          else return { valid: false, errorMessage: 'Your email isn\'t right' };
        }
        case 'onlyNumbers': {
          if (onlyNumbersValidator(value)) break;
          else return { valid: false, errorMessage: 'You should type only numbers in this field' };
        }
        case 'onlyNumbersAndDecimalNumbers': {
          if (onlyNumbersAndDecimalNumbersValidator(value)) break;
          else return { valid: false, errorMessage: 'You should type only numbers in this field' };
        }
        case 'ageRule': {
          if (ageValidator(value)) break;
          else return { valid: false, errorMessage: 'Your age should be less than 140' };
        }
        case 'weightRule': {
          if (weightValidator(value)) break;
          else return { valid: false, errorMessage: 'Your weight should be less than 600kg' };
        }
        case 'heightRule': {
          if (heightValidator(value)) break;
          else return { valid: false, errorMessage: 'Your height should be less than 3 meter' };
        }
        case 'positiveRule': {
          if (positiveValidator(value)) break;
          else return { valid: false, errorMessage: 'The number that you typed should be positive' };
        }
        default:
      }
    }
  }

  return { valid: true, errorMessage: '' };
};

export default validate;
