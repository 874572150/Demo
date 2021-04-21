import {get,put,post,del} from './request'

export default class BaseApi {
  static $get = get;
  static $put = put;
  static $post = post;
  static $del = del;
}