import AuthorizedFunction from '../../utils/AuthorizedFunction';

export default function AuthrizedElement({ roles, children }) {
  return AuthorizedFunction(roles) && children;
}
